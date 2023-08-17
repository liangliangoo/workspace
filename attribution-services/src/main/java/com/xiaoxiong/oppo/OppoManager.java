package com.xiaoxiong.oppo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.xiaoxiong.utils.MD5Util;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

/**
 * @Author 六月
 * @Date 2023/8/17 14:20
 * @Version 1.0
 */
@Slf4j
public class OppoManager {

    @SneakyThrows
    public static void main(String[] args) {
        OppoManager oppoManager = new OppoManager();
        oppoManager.attr();
    }

    public void attr() throws IOException {
        List<String> oaIds = Lists.newArrayList(
                "689407051d0bbedde9cfa1c2260f361b2fc1cb26796b9180721c03500c5cb929",
                "8eccbaa10bc56fb4f9d23b5fd961b3306d63bb75dd7041b0997b22efec569d3f",
                "ba6d1e279e92a11d40999a3d4961bd9cef46d006940df32a826db278378292a0",
                "bcefe339127d0f1dbe0b07fd19237bea95f6bed7a0e330e088bae5dbf176a83b",
                "4d2471b1edc316d5866886154e18fd70c4bb2674c1e1d21fb2ce84e627a9ad10",
                "5572f9b0a8443449e1d77e07dbc5c302733b28d812890c848b7c0341e2853b54",
                "1b33047d949b0c5ce1aae22cdbafcbe103c8558e5ec7c7d56098b44b8b84b1fb",
                "71400a96c2170c8a53d227e2ac56a2b0147844e669f6ce13b8ab9e8dbe777998",
                "dc8ac0cf1798d3b2307270651d739a130a7b3f33d9705c72e9a518338c3dc5cf",
                "57c01c0a2f3f74bdd0a724176d160ca8041cfe24e070c993b028f9c9049a92c6");

        OppoUserDataVO build = OppoUserDataVO.builder()
                .type(2)
                .dataType(7)
                .channel(1)
                .payAmount(10000L)
                .timestamp(System.currentTimeMillis())
                .pkg("com.huayu.lianainiang")
                .ascribeType(0)
                .build();
        for (String oaId : oaIds) {
            build.setOuId(MD5Util.encodeByBase64(oaId, "XGAXicVG5GMBsx5bueOe4w=="));
            System.out.println(sendToOppo(build, "https://api.ads.heytapmobi.com/api/uploadActiveData", "e0u6fnlag06lc3pl"));
        }
    }

    /**
     * 上传用户数据方法
     *
     * @param postData 使用请求参数的json串替换
     * @return
     * @throws IOException
     */
    private String sendToOppo(OppoUserDataVO postData, String url, String oppoSalt) throws IOException {
        log.info("sendToOppo postData {} url {} oppoSalt {}", JSON.toJSONString(postData), url, oppoSalt);
        Long timestamp = postData.getTimestamp();
        String data = JSONObject.toJSONString(postData);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, data);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("timestamp", timestamp.toString())
                .addHeader("signature", getSignature(postData, timestamp, oppoSalt))
                .build();
        log.info("sendToOppo url {} postData {} timestamp {} signature {}", url, data, timestamp, getSignature(postData, timestamp, oppoSalt));
        Response response = client.newCall(request).execute();
        assert response.body() != null;
        return response.body().string();
    }


    /**
     * @param userDataVO OppoUserDataVO  post请求的数据
     * @param timestamp  请求头中的时间戳
     * @return string
     */
    private String getSignature(OppoUserDataVO userDataVO, long timestamp, String OPPO_SALT) {
        log.info("postData:{},timestamp:{}", userDataVO, timestamp);
        String signature = JSONObject.toJSONString(userDataVO) + timestamp + OPPO_SALT;
        log.debug("before encode signature {}", signature);
        String ans = MD5Util.encode(signature).toLowerCase();
        log.debug("signature:{}", ans);
        return ans;
    }

}
