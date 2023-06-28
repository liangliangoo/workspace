package com.xiaoxiong.bumblebee.chat.websocket;

import com.xiaoxiong.bumblebee.chat.websocket.handler.WebSocketChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author 六月
 * @Date 2023/6/27 20:39
 * @Version 1.0
 */
@Slf4j
@Component
public class WebSocketManager {

    private static ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 2 * Runtime.getRuntime().availableProcessors(),
            60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));


    @PostConstruct
    public void init() {
        log.info("init 初始化netty服务器 开始");
        executorService.execute(() -> start());
        log.info("init 初始化netty服务器 结素");
    }

    private void start() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new WebSocketChannelHandler());
        try {
            Channel channel = bootstrap.bind(8888).sync().channel();
            log.info("start 绑定 8888端口");
            channel.closeFuture().sync();
            log.info("start  channel.closeFuture().sync()");
        } catch (InterruptedException e) {
            log.error("start error caused by {}", e);
        } finally {
            log.info("start 关闭netty 线程池");
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

}
