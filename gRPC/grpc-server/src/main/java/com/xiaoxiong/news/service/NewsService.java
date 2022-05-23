package com.xiaoxiong.news.service;

import com.xiaoxiong.news.proto.NewsProto;
import com.xiaoxiong.news.proto.NewsServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.Date;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/5/17 13:19
 */
public class NewsService extends NewsServiceGrpc.NewsServiceImplBase {

    @Override
    public void list(NewsProto.NewsRequest request, StreamObserver<NewsProto.NewsResponse> responseObserver) {
        String date = request.getDate();
        NewsProto.NewsResponse response = null;
        try {
            NewsProto.NewsResponse.Builder builder = NewsProto.NewsResponse.newBuilder();
            for (int i = 0; i < 100; i++) {
                NewsProto.News news = NewsProto.News.newBuilder()
                        .setId(i)
                        .setContent(date + " this news contents is:" + i)
                        .setTitle("news-" + i)
                        .setCreateTime(new Date().getTime())
                        .build();
                builder.addNews(news);
            }
            response = builder.build();
        } catch (Exception e) {
            responseObserver.onError(e);
        } finally {
            // 返回数据
            responseObserver.onNext(response);
        }
        // 通知网络处理完毕
        responseObserver.onCompleted();
    }

}
