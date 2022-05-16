package com.xiaoxiong.webfluxclient.proxy;

import com.xiaoxiong.webfluxclient.annotation.ApiServer;
import com.xiaoxiong.webfluxclient.domain.MethodInfo;
import com.xiaoxiong.webfluxclient.domain.ServerInfo;
import com.xiaoxiong.webfluxclient.interfaces.RestHandler;
import com.xiaoxiong.webfluxclient.interfaces.WebClientRestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpMethod.*;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/11  19:38
 */
@Component
@Slf4j
public class JdkProxyCreator implements ProxyCreator {
    @Override
    public Object createProxy(Class<?> type) {

        log.info("createProxy:" + type);
        //根据接口获取API服务器
        ServerInfo serverInfo = extractServerInfo(type);
        log.info("serverInfo:" + serverInfo);
        RestHandler handler = new WebClientRestHandler();
        // 初始化服务器信息（初始化webclient）
        handler.init(serverInfo);

        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{type}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                // 根据方法和参数获取调用信息
                MethodInfo methodInfo = extractMethodInfo(method, args);
                log.info("methodInfo:" + methodInfo);
                return handler.invokeRest(methodInfo);

            }
        });
    }

    private ServerInfo extractServerInfo(Class<?> type) {
        ServerInfo serverInfo = new ServerInfo();
        ApiServer annotation = type.getAnnotation(ApiServer.class);
        serverInfo.setUrl(annotation.value());
        return serverInfo;
    }

    private MethodInfo extractMethodInfo(Method method, Object[] args) {

        MethodInfo methodInfo = new MethodInfo();
        extractedUrlAndMethod(method, methodInfo);

        extractedParamsAndBody(method, args, methodInfo);
        extractedReturnInfo(method, methodInfo);
        return methodInfo;
    }

    private void extractedReturnInfo(Method method, MethodInfo methodInfo) {
        Class<?> returnType = method.getReturnType();
        methodInfo.setReturnFlux(returnType.isAssignableFrom(Flux.class));
        extractedReturnElementType(method, methodInfo);
    }

    private void extractedReturnElementType(Method method, MethodInfo methodInfo) {
        ParameterizedType genericReturnType = (ParameterizedType) method.getGenericReturnType();
        Type[] actualTypeArguments = genericReturnType.getActualTypeArguments();
        methodInfo.setReturnElementType(((Class<?>) actualTypeArguments[0]));
    }

    private void extractedParamsAndBody(Method method, Object[] args, MethodInfo methodInfo) {
        Map<String, Object> params = new HashMap<>();
        methodInfo.setParams(params);
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            PathVariable pathVariable = parameters[i].getAnnotation(PathVariable.class);
            if (pathVariable != null) {
                params.put(pathVariable.value(), args[i]);
            }
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                methodInfo.setBody(((Mono<?>) args[i]));
                ParameterizedType parameterizedType = (ParameterizedType) parameters[i].getParameterizedType();
                methodInfo.setBodyElementType(((Class<?>) parameterizedType.getActualTypeArguments()[0]));
            }
        }
    }

    private void extractedUrlAndMethod(Method method, MethodInfo methodInfo) {
        for (Annotation annotation : method.getAnnotations()) {
            if (annotation instanceof GetMapping) {
                GetMapping getMapping = (GetMapping) annotation;
                methodInfo.setUrl(getMapping.value()[0]);
                methodInfo.setMethod(GET);
            } else if (annotation instanceof PostMapping) {
                PostMapping postMapping = (PostMapping) annotation;
                methodInfo.setUrl(postMapping.value()[0]);
                methodInfo.setMethod(POST);
            } else if (annotation instanceof PutMapping) {
                PutMapping putMapping = (PutMapping) annotation;
                methodInfo.setUrl(putMapping.value()[0]);
                methodInfo.setMethod(PUT);
            } else if (annotation instanceof DeleteMapping) {
                DeleteMapping deleteMapping = (DeleteMapping) annotation;
                methodInfo.setUrl(deleteMapping.value()[0]);
                methodInfo.setMethod(DELETE);
            }
        }
    }
}
