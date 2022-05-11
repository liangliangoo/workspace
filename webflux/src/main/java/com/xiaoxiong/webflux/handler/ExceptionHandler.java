package com.xiaoxiong.webflux.handler;

import com.xiaoxiong.webflux.exceptions.MyCheckException;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.TEXT_PLAIN;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/11  15:21
 */
@Order(-2)
@Component
public class ExceptionHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(BAD_REQUEST);
        response.getHeaders().setContentType(TEXT_PLAIN);
        // 异常信息
        String errorMsg = toStr(ex);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(errorMsg.getBytes())));
    }

    private String toStr(Throwable ex) {
        if (ex instanceof MyCheckException) {
            MyCheckException exception = (MyCheckException) ex;
            return exception.getFieldName() + ":" + "invalid value==>" + exception.getFieldValue();
        } else {
            ex.printStackTrace();
            return ex.toString();
        }
    }
}
