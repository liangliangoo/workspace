package com.xiaoxiong.news;

import com.xiaoxiong.news.proto.NewsProto;
import com.xiaoxiong.news.proto.NewsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/5/17 13:37
 */
public class GrpcClient {

    public static final int port = 9090;
    public static final String address = "localhost";

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext()
                .build();
        try {
            NewsServiceGrpc.NewsServiceBlockingStub blockingStub = NewsServiceGrpc
                    .newBlockingStub(channel);
            NewsProto.NewsResponse newsResponse = blockingStub.list(NewsProto.NewsRequest.newBuilder().setDate("20220510").build());
            List<NewsProto.News> newsList = newsResponse.getNewsList();
            for (NewsProto.News news : newsList) {
                System.out.println(news);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channel.shutdown();
        }

    }

}
