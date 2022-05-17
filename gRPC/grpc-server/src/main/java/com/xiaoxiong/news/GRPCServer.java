package com.xiaoxiong.news;

import com.xiaoxiong.news.service.NewsService;
import com.xiaoxiong.news.service.SmsService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/5/17 13:29
 */
public class GRPCServer {

    private static final int port = 9090;

    public static void main(String[] args) {
        Server server = null;
        try {
            server = ServerBuilder.forPort(port)
                    .addService(new NewsService())
                    .addService(new SmsService())
                    .build()
                    .start();
            System.out.println("server启动成功");
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
