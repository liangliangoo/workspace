package com.xiaoxiong.reactordemo.reactor1;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 用来处理连接请求
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/12  21:01
 */
public class Acceptor implements Runnable{

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    public Acceptor(Selector selector, ServerSocketChannel serverSocketChannel) {
        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
    }

    /**
     * 处理连接
     */
    @Override
    public void run() {
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println("有client连接，" + socketChannel.getRemoteAddress());
            socketChannel.configureBlocking(false);
            // 只处理读操作
            SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
            // 将其放入到响应事件中，等待服务器处理
            selectionKey.attach(new WorkHandler(socketChannel));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
