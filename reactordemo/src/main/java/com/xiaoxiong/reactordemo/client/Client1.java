package com.xiaoxiong.reactordemo.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/12  19:35
 */
public class Client1 {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 8080);
            byte[] bytes = "hello I'm client".getBytes(StandardCharsets.UTF_8);
            socket.getOutputStream().write(bytes);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
