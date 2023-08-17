package com.xiaoxiong.vivo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.UUID;

/**
 * @Author 六月
 * @Date 2023/8/17 12:10
 * @Version 1.0
 */
@Slf4j
public class VivoManager {

    private static final JSONObject param = JSON.parseObject("{\n" +
            "    \"dataList\": [\n" +
            "        {\n" +
            "            \"cvTime\": 1692245790477,\n" +
            "            \"cvType\": \"PAY\",\n" +
            "            \"dlrSrc\": \"vivo\",\n" +
            "            \"userId\": \"fd658618a25262a65ba487837aa5a75d8e9cca815bd050dbb354e48e827cacfa\",\n" +
            "            \"userIdType\": \"OAID\",\n" +
            "            \"cvParam\": \"10000\",\n" +
            "            \"requestId\": \"6e2df854d4dba5a11692122238682\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"pkgName\": \"com.huayu.lianainiang\",\n" +
            "    \"srcId\": \"ds-202308034850\",\n" +
            "    \"srcType\": \"APP\"\n" +
            "}");

    private static final VivoInfo vivoInfo = JSON.parseObject("{\"utmSource\":\"OCPD_VIVO_V3\",\"srcId\":\"ds-202308034850\",\"advertiserId\":\"f7efc2cd7d3d908b124a\",\"pkgName\":\"com.huayu.lianainiang\",\"token\":\"bf410e6ff005a6f50dcc0768988e0fbbaeaaaf58a517d48f25696440a5eaff8e\",\"attrType\":\"selfAttr\",\"vestChannel\":\"lianainiang\"}", VivoInfo.class);

    public void attr() {
        JSONArray dataList = param.getJSONArray("dataList");

    }

    @SneakyThrows
    public void send(VivoInfo vivoInfo, JSONObject jsonStr) {
        String url = vivoInfo.getUrl();
        String advertiserId = vivoInfo.getAdvertiserId();
        // nounce 是随机字串标识，不超过32个字符，由调用方自行生成，需保证全局唯一性。以Query Parameter方式在请求路径中传递
        String s = UUID.randomUUID().toString().replaceAll("-", "");
        String nonce = s;
        String accessToken = vivoInfo.token;
        log.info("send token {} ", accessToken);
        String timestamp = System.currentTimeMillis() + "";
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonStr.toJSONString());
        Request request = new Request.Builder()
                .url(url + "?access_token=" + accessToken + "&timestamp=" + timestamp + "&nonce=" + nonce + "&advertiser_id=" + advertiserId)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        log.info("sendToVIvo url {}", request.url().toString());
        Response response = client.newCall(request).execute();
        log.info(response.body().string());
    }

    @Data
    static class VivoInfo {
        private String custom;
        private String advertiserId;
        private String pkgName;
        private String srcId;
        private String url;
        private String token;
    }

}
