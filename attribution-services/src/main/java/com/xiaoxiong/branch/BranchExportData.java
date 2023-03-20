package com.xiaoxiong.branch;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @Author 六月
 * @Date 2023/3/20 14:48
 * @Version 1.0
 */
public class BranchExportData {

    public static void main(String[] args) throws IOException {
        LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.UTC).plusDays(-1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        OkHttpClient client = new OkHttpClient();
        JSONObject param = new JSONObject();
        param.put("branch_key", "key_live_aj07HY6r5GVRutuD9x0b4cghsuosCn3A");
        param.put("branch_secret", "secret_live_G6CalBGfozhOXckeh1U3ElPXR97epYjM");
        param.put("export_date", dateTime.format(formatter));
        String data = param.toJSONString();
        System.out.println(data);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, data);
        Request request = new Request.Builder()
                .url("https://api2.branch.io/v3/export")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    public void test() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"data_source\":\"eo_install\",\"aggregation\":\"cost\",\"dimensions\":[\"attributed\"],\"enable_install_recalculation\":false,\"granularity\":\"all\",\"ordered_by\":\"attributed\",\"ordered\":\"descending\",\"zero_fill\":false,\"end_date\":\"2023-03-18\",\"start_date\":\"2023-03-17\",\"branch_key\":\"ey_live_aj07HY6r5GVRutuD9x0b4cghsuosCn3A\",\"branch_secret\":\"secret_live_G6CalBGfozhOXckeh1U3ElPXR97epYjM\"}");
        Request request = new Request.Builder()
                .url("https://api2.branch.io/v1/query/analytics")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
    }

}
