package com.xiaoxiong.branch;

import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class BranchExportDataTest {


    @Test
    public void testQueryApi() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"data_source\":\"eo_install\",\"aggregation\":\"cost\",\"dimensions\":[\"attributed\"]," +
                "\"enable_install_recalculation\":false,\"granularity\":\"all\",\"ordered_by\":\"attributed\",\"ordered\":\"descending\",\"zero_fill\":false,\"end_date\":\"2023-03-18\",\"start_date\":\"2023-03-17\"," +
                "\"branch_key\":\"key_live_aj07HY6r5GVRutuD9x0b4cghsuosCn3A\",\"branch_secret\":\"secret_live_G6CalBGfozhOXckeh1U3ElPXR97epYjM\"}");
        Request request = new Request.Builder()
                .url("https://api2.branch.io/v1/query/analytics")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }


}