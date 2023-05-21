package com.xiaoxiong.opposdk;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.squareup.okhttp.*;
import com.xiaoxiong.base.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 六月
 * @Date 2023/5/19 10:43
 * @Version 1.0
 */
@Slf4j
public class OppoHttpRequestTest {


    @Test
    public void testRequest() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("token", "MTAwMDE4NTE4MywwYjUxOTI2MjVjNzk0MjQ0YjA2ZTVmNjUyNjNiODFhMSwxNjg0NDY0Mzk2LDVmMmQ0ODFhZWFhODg3NzE4OWVlYTI0Y2U0Yzk3YWYwMTRiZjA2ZDA=");
        log.info("buildRequest param {}", JSON.toJSONString(params));
        Object token = params.get("token");
        Request.Builder builder = new Request.Builder();
        builder.url("https://sapi.ads.heytapmobi.com/v3/data/common/summary/queryAdData");
        builder.header("Authorization", "Bearer " + token.toString());
        builder.header("Content-type","application/json");
        JSONObject postBody = new JSONObject();
        postBody.put("beginTime", DateUtil.getYesterdayYyyyMMdd());
        postBody.put("endTime", DateUtil.getNowYyyyMMdd());
        postBody.put("timeLevel", "DAY");
        String data = postBody.toJSONString();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), data);
        builder.post(body);
        log.info("oppo buildRequest postBody {} header token {}", data, params.get("token"));
        Request request = builder.build();
        OkHttpClient client = new OkHttpClient();
        try {
            Response execute = client.newCall(request).execute();
            System.out.println(execute.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test() throws IOException {
        Map<String, Object> params = Maps.newHashMap();
        params.put("token", "MTAwMDE4NTE4MywwYjUxOTI2MjVjNzk0MjQ0YjA2ZTVmNjUyNjNiODFhMSwxNjg0NDY0Mzk2LDVmMmQ0ODFhZWFhODg3NzE4OWVlYTI0Y2U0Yzk3YWYwMTRiZjA2ZDA=");
        log.info("buildRequest param {}", JSON.toJSONString(params));
        Object token = params.get("token");
        JSONObject postBody = new JSONObject();
        postBody.put("beginTime", DateUtil.getYesterdayYyyyMMdd());
        postBody.put("endTime", DateUtil.getNowYyyyMMdd());
        postBody.put("timeLevel", "DAY");
        String data = postBody.toJSONString();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), data);
        okhttp3.Request request = new okhttp3.Request.Builder().url("https://sapi.ads.heytapmobi.com/v3/data/common/summary/queryAdData")
                .header("Authorization", "Bearer " + token.toString())
                .header("Content-type", "application/json; charset=utf-8")
                .post(okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json"), data)).build();
        okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient();
        okhttp3.Response response = okHttpClient.newCall(request).execute();
        System.out.println(response.body().string());
    }

    @Test
    public void huToolPost() {
        JSONObject postBody = new JSONObject();
        postBody.put("beginTime", DateUtil.getYesterdayYyyyMMdd());
        postBody.put("endTime", DateUtil.getNowYyyyMMdd());
        postBody.put("timeLevel", "DAY");
        String data = postBody.toJSONString();
        Map<String, String > heads = new HashMap<>();
        // 使用json发送请求，下面的是必须的
        heads.put("Content-Type", "application/json;charset=UTF-8");
        heads.put("Authorization", "Bearer MTAwMDE4NTE4MywwYjUxOTI2MjVjNzk0MjQ0YjA2ZTVmNjUyNjNiODFhMSwxNjg0NDY2OTM3LDJjNjg2NDZhMjYzNmZlNmI3NTM1YTAwMGVjZGI3OWFhNWQyNjJjMWY=");
        HttpResponse response = HttpRequest.post("https://sapi.ads.heytapmobi.com/v3/data/common/summary/queryAdData")
                .headerMap(heads, false)
                .body(data)
                .timeout(5 * 60 * 1000)
                .execute();
        System.out.println(response.body().toString());
    }


}
