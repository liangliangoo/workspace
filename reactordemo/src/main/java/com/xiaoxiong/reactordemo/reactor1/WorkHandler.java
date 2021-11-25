package com.xiaoxiong.reactordemo.reactor1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * 负责处理客户端写事件的
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/12  21:19
 */
public class WorkHandler implements Runnable {

    private SocketChannel socketChannel;

    public WorkHandler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            socketChannel.read(buffer);
            String message = new String(buffer.array(), StandardCharsets.UTF_8);
            System.out.println(socketChannel.getRemoteAddress() + "发来的消息是：" + message);
            socketChannel.write(ByteBuffer.wrap("我收到了你的消息".getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
