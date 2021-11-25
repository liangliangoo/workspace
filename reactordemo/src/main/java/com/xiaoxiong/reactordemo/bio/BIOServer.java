package com.xiaoxiong.reactordemo.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 传统BIO操作
 * Server端
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/12  19:16
 */
public class BIOServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            new Thread(()->{
                try {
                    byte[] read = new byte[1024];
                    socket.getInputStream().read(read);

                    String req = new String(read, StandardCharsets.UTF_8); //encode

                    // 处理具体的业务逻辑
                    System.out.println(req);

                    byte[] write = "hello xioaxiong".getBytes(StandardCharsets.UTF_8); //decode
                    socket.getOutputStream().write(write);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
