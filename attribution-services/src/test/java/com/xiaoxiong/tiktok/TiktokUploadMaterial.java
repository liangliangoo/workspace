package com.xiaoxiong.tiktok;

import okhttp3.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author 六月
 * @Date 2023/9/20 12:07
 * @Version 1.0
 */
public class TiktokUploadMaterial {

        private static final String ACCESS_TOKEN = "9169411d5ff4e6e0f9edc8f24e213d7451a39950";
        private static final String PATH = "/open_api/v1.3/file/video/ad/upload/";

        private static String buildUrI(String path) throws URISyntaxException {
            URI uri = new URI("https", "business-api.tiktok.com", path, "", "");
            return uri.toString();
        }

        private static String post(Long advertiserId, String videoUrl, String uploadType) throws URISyntaxException, IOException {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("advertiser_id",advertiserId.toString())
                    .addFormDataPart("upload_type",uploadType)
                    .addFormDataPart("video_url",videoUrl)
                    .addFormDataPart("auto_fix_enabled","true")
                    .addFormDataPart("auto_bind_enabled","true")
                    .addFormDataPart("flaw_detect","true")
                    .build();
            Request request = new Request.Builder()
                    .url(buildUrI(PATH))
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Access-Token", ACCESS_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }

        public static void main(String[] args) throws URISyntaxException, IOException {
            Long advertiserId = 7275989408527122434L;
            String videoUrl = "https://res-cdn.nuan.chat/res/prod/delivery/sourceMaterial/yule/AMO20230915225841_(AMO_0807)_en.mp4";
            String uploadType = "UPLOAD_BY_URL";
            // System.out.println(post(advertiserId, videoUrl, uploadType));
            // System.out.println(String.format("%.2f", Float.valueOf("1000") / 100));
        }




}
