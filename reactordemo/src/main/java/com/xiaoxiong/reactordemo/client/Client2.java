package com.xiaoxiong.reactordemo.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/12  19:54
 */
public class Client2 {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(InetAddress.getLocalHost(),8080));
            new Thread(() -> {
                while (true) {
                    try {
                        byte[] read = new byte[1024];
                        socket.getInputStream().read(read);
                        System.out.println(new String(read, StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            while (true) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    socket.getOutputStream().write(s.getBytes(StandardCharsets.UTF_8));
                }
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
