package com.xiaoxiong.webfluxclient.interfaces;

import com.xiaoxiong.webfluxclient.domain.MethodInfo;
import com.xiaoxiong.webfluxclient.domain.ServerInfo;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/11  20:30
 */
public class WebClientRestHandler implements RestHandler {

    private WebClient client;

    /**
     * 初始化webClint
     *
     * @param serverInfo
     */
    @Override
    public void init(ServerInfo serverInfo) {
        this.client = WebClient.create(serverInfo.getUrl());
    }

    /**
     * 处理rest请求
     *
     * @param methodInfo
     * @return
     */
    @Override
    public Object invokeRest(MethodInfo methodInfo) {
        Object result = null;
        WebClient.RequestBodySpec request = client.method(methodInfo.getMethod())
                .uri(methodInfo.getUrl(), methodInfo.getParams())
                .accept(MediaType.APPLICATION_JSON);
        WebClient.ResponseSpec retrieve = null;
        if (methodInfo.getBody() != null) {
            retrieve = request.body(methodInfo.getBody(), methodInfo.getBodyElementType()).retrieve();
        } else {
            retrieve = request.retrieve();
        }

        // 处理异常
        retrieve.onStatus(status -> status.value() == 404,
                response -> Mono.just(new RuntimeException("Not Found!")));


        if (methodInfo.getReturnFlux()) {
            result = retrieve.bodyToFlux(methodInfo.getReturnElementType());
        } else {
            result = retrieve.bodyToMono(methodInfo.getReturnElementType());
        }

        return result;
    }
}
