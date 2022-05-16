package com.xiaoxiong.webfluxclient.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author xiongliang
 * @version 1.0
 * @description 方法调用信息类
 * @since 2022/5/11  19:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MethodInfo {

    /**
     * 请求的URL
     */
    private String url;

    /**
     * 请求的类型
     */
    private HttpMethod method;

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    /**
     * 请求体
     */
    private Mono<?> body;

    private Class<?> bodyElementType;

    /**
     * 返回的是Flux好还是mono
     */
    private Boolean returnFlux;

    /**
     * 返回的数据类型
     */
    private Class<?> returnElementType;
}
