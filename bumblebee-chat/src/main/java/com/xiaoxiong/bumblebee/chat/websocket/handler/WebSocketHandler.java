package com.xiaoxiong.bumblebee.chat.websocket.handler;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.xiaoxiong.bumblebee.chat.config.ChannelGroupConfig;
import com.xiaoxiong.bumblebee.chat.model.ChatRecord;
import com.xiaoxiong.bumblebee.chat.service.NettyService;
import com.xiaoxiong.bumblebee.chat.utils.SpringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用channelUserMap 存储所有的连接用户键为：用户的code，值为：通道对象
 *
 * @Author 六月
 * @Date 2023/6/27 21:46
 * @Version 1.0
 */
@Slf4j
public class WebSocketHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * key -> userId
     * value -> channel
     */
    private static ConcurrentHashMap<String, Channel> channelUserMap = new ConcurrentHashMap<>();

    private WebSocketServerHandshaker webSocketServerHandshaker;
    private NettyService nettyService;



    /**
     * 每当服务端收到新的客户端连接时,客户端的channel存入ChannelGroup列表中,并通知列表中其他客户端channel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //获取连接的channel
        log.info("handlerAdded,连接channel{} 连接id{}", ctx.channel(), ctx.channel().id());
        ChannelGroupConfig.group.add(ctx.channel());
    }


    /**
     * 每当服务端断开客户端连接时,客户端的channel从ChannelGroup中移除,并通知列表中其他客户端channel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //从服务端的channelGroup中移除当前离开的客户端
        ChannelGroupConfig.group.remove(channel);

        //获得删除channle对应的userCode
        String removeUserCode = "";
        for (String userCode : channelUserMap.keySet()) {
            Channel userChannel = channelUserMap.get(userCode);
            if (userChannel.equals(channel)) {
                removeUserCode = userCode;
                break;
            }
        }

        //从服务端的channelMap中移除当前离开的客户端
        Collection<Channel> col = channelUserMap.values();
        while (col.contains(channel)) {
            col.remove(ctx.channel());
            log.info("handlerRemoved,netty客户端连接删除成功!,删除channel:{},channelId:{}", ctx.channel(), ctx.channel().id());
        }
        //通知好友上线下线通知
        sendFriendMsgLoginOrOut(removeUserCode, "notice", "下线了");
    }

    private void sendFriendMsgLoginOrOut(String removeUserCode, String notice, String msg) {

    }


    /**
     * 服务端监听到客户端活动
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive,netty与客户端建立连接，通道开启！channel{}连接,连接id{}", ctx.channel(), ctx.channel().id());
    }

    /**
     * 服务端监听到客户端不活动
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelInactive,netty与客户端断开连接，通道关闭！channel:{},channelId:{}", ctx.channel(), ctx.channel().id());
    }

    //工程出现异常的时候调用
    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable) throws Exception {
        log.info("exceptionCaught,抛出异常，异常信息{}，异常信息channel:{},channelId:{}", throwable.getLocalizedMessage(), context.channel(), context.channel().id());
        handlerRemoved(context);
        context.close();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        //处理客户端向服务端发起的http握手请求
        if (o instanceof FullHttpRequest) {
            log.info("http连接请求");
            handHttpRequest(channelHandlerContext, (FullHttpRequest) o);
        } else if (o instanceof WebSocketFrame) {//处理websocket链接业务
            log.info("websocket信息请求");
            handWebSocketFrame(channelHandlerContext, (WebSocketFrame) o);
        }
    }

    /**
     * 处理客户端与服务端之间的websocket业务
     *
     * @param context
     * @param webSocketFrame
     */
    private void handWebSocketFrame(ChannelHandlerContext context, WebSocketFrame webSocketFrame) {
        if (webSocketFrame instanceof CloseWebSocketFrame) {//判断是否是关闭websocket的指令
            webSocketServerHandshaker.close(context.channel(), (CloseWebSocketFrame) webSocketFrame.retain());
        }
        if (webSocketFrame instanceof PingWebSocketFrame) {//判断是否是ping消息
            context.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
            return;
        }
        if (!(webSocketFrame instanceof TextWebSocketFrame)) {//判断是否是二进制消息
            System.out.println("不支持二进制消息");
            throw new RuntimeException(this.getClass().getName());
        }
        //获取客户端向服务端发送的消息
        String text = ((TextWebSocketFrame) webSocketFrame).text();
        log.info("服务端收到客户端的消息：" + text);
        ChatRecord chatRecord = exchangeChatMessage(context.channel(), text);
        //接收信息的userCode
        String toCode = chatRecord.getToCode();
        //判断发送的code是否是群聊code
        List<String> listCode = nettyService.queryGroupChatUsers(toCode);
        if (!CollectionUtils.isEmpty(listCode)) {
            //群聊 给群里的每个人都发
            listCode.forEach(v -> {
                //服务端向好友客户端发送消息
                if (channelUserMap.containsKey(v) && !v.equals(chatRecord.getFromCode())) {
                    channelUserMap.get(v).writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(chatRecord)));
                }
            });

        } else {
            //单聊
            //服务端向好友客户端发送消息
            if (channelUserMap.containsKey(toCode)) {
                channelUserMap.get(toCode).writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(chatRecord)));
            }
        }
    }

    /**
     * 发送的信息转换
     *
     * @throws
     * @Title: exchangeChatMessage
     * @author: dy.yin 2021/4/22 13:02
     * @param: [channel, text]
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    private ChatRecord exchangeChatMessage(Channel channel, String text) {

        JSONObject chatRecordJson = JSONObject.parseObject(text);
        ChatRecord chatRecord = JSON.toJavaObject(chatRecordJson, ChatRecord.class);
        chatRecord.setMessageTime(new Timestamp(System.currentTimeMillis()));
        nettyService = SpringUtils.getBean(NettyService.class);
        nettyService.insertChatRecord(chatRecord);

        return chatRecord;
    }

    /**
     * 处理客户端向服务端发起http握手请求业务
     *
     * @param context
     * @param fullHttpRequest
     */
    private void handHttpRequest(ChannelHandlerContext context, FullHttpRequest fullHttpRequest) {
        log.info("请求连接的channel{}，id为{}", context.channel(), context.channel().id());
        //判断是否http握手请求
        if (!fullHttpRequest.decoderResult().isSuccess()
                || !("websocket".equals(fullHttpRequest.headers().get("Upgrade")))) {
            sendHttpResponse(context, fullHttpRequest, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        nettyService = SpringUtils.getBean(NettyService.class);
        String webSocketUrl = nettyService.getWebSocketUrl();
        WebSocketServerHandshakerFactory webSocketServerHandshakerFactory = new WebSocketServerHandshakerFactory(webSocketUrl, null, false);
        webSocketServerHandshaker = webSocketServerHandshakerFactory.newHandshaker(fullHttpRequest);
        if (webSocketServerHandshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(context.channel());
        } else {
            webSocketServerHandshaker.handshake(context.channel(), fullHttpRequest);
        }
        //把token解析成用户Code
        Channel channel = context.channel();
        String uri = fullHttpRequest.uri();
        String userCode = uri.substring(uri.lastIndexOf("?") + 1, uri.length());
        channelUserMap.put(userCode, channel);

        sendFriendMsgLoginOrOut(userCode, "notice", "上线了");

    }

    /**
     * 服务端想客户端发送响应消息
     *
     * @param context
     * @param fullHttpRequest
     * @param defaultFullHttpResponse
     */
    private void sendHttpResponse(ChannelHandlerContext context, FullHttpRequest fullHttpRequest, DefaultFullHttpResponse defaultFullHttpResponse) {
        if (defaultFullHttpResponse.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(defaultFullHttpResponse.getStatus().toString(), CharsetUtil.UTF_8);
            defaultFullHttpResponse.content().writeBytes(buf);
            buf.release();
        }
        //服务端向客户端发送数据
        ChannelFuture future = context.channel().writeAndFlush(defaultFullHttpResponse);
        if (defaultFullHttpResponse.getStatus().code() != 200) {
            future.addListener(ChannelFutureListener.CLOSE);
        }

    }


}
