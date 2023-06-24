package com.xiaoxiong.server.jdk;

import com.alibaba.fastjson.JSON;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 六月
 * @Date 2023/6/21 15:59
 * @Version 1.0
 */
public class JdkHttpServer {

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
        httpServer.createContext("/server/jdk-http/hello-word-get", exchange -> {
            String requestMethod = exchange.getRequestMethod();
            System.out.println(requestMethod);
            if (StringUtils.equalsIgnoreCase(requestMethod, "get")) {
                //设置服务端响应的编码格式，否则在客户端收到的可能是乱码
                Headers responseHeaders = exchange.getResponseHeaders();
                responseHeaders.set("Content-Type", "application/json");

                // 获取get请求参数
                URI requestURI = exchange.getRequestURI();
                System.out.println("requestURI requestParam " + JSON.toJSONString(queryToMap(requestURI.getQuery())));
                String response = "{\"code\": 100,\"data\":\"hello get request\"}";

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.getBytes("UTF-8").length);

                OutputStream responseBody = exchange.getResponseBody();
                OutputStreamWriter writer = new OutputStreamWriter(responseBody, "UTF-8");
                writer.write(response);
                writer.close();
                responseBody.close();
            } else {
                Headers responseHeaders = exchange.getResponseHeaders();
                responseHeaders.set("Content-Type", "text/html;charset=utf-8");
                String response = "<a>error</a>";
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, response.getBytes(StandardCharsets.UTF_8).length);
                OutputStream responseBody = exchange.getResponseBody();
                OutputStreamWriter writer = new OutputStreamWriter(responseBody, "UTF-8");
                writer.write(response);
                writer.close();
                responseBody.close();
            }
        });
        httpServer.setExecutor(null);
        httpServer.start();
    }


    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0],"");
            }
        }
        return result;
    }

}
