package com.xiaoxiong.flink.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Map;

/**
 * @Author 六月
 * @Date 2023/7/25 16:04
 * @Version 1.0
 */
@Slf4j
public class OkHttpUtil {

    public static Request buildGetRequest(String url, Map<String, Object> params, String[] headers) {
        StringBuilder sb = new StringBuilder(url);
        if (params != null && params.keySet().size() > 0) {
            boolean firstFlag = true;
            for (String key : params.keySet()) {
                if (firstFlag) {
                    sb.append("?").append(key).append("=").append(params.get(key));
                    firstFlag = false;
                } else {
                    sb.append("&").append(key).append("=").append(params.get(key));
                }
            }
        }
        Request.Builder builder = new Request.Builder();
        if (headers != null && headers.length > 0) {
            if (headers.length % 2 == 0) {
                for (int i = 0; i < headers.length; i = i + 2) {
                    builder.addHeader(headers[i], headers[i + 1]);
                }
            } else {
                log.warn("headers's length[{}] is error.", headers.length);
            }

        }
        log.debug("buildGetRequest {}", builder.url(sb.toString()).build());
        return builder.url(sb.toString()).build();
    }

    public static Request buildPostRequest(String url, String body, String[] headers) {
        log.debug("buildPostRequest url {} body {} headers {}", url, body, JSON.toJSONString(headers));
        Request.Builder builder = new Request.Builder();
        if (headers != null && headers.length > 0) {
            if (headers.length % 2 == 0) {
                for (int i = 0; i < headers.length; i = i + 2) {
                    builder.addHeader(headers[i], headers[i + 1]);
                }
            } else {
                log.warn("headers's length[{}] is error.", headers.length);
            }
        }
        Request post = builder.url(url).method("POST", RequestBody.create(MediaType.parse("application/json"), body)).build();
        log.debug("buildPostRequest {} body {}", post);
        return post;
    }

}
