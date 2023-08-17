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
                "13B208512D9344D5848DA9F4B994782F7fc47557d52c38c3c2c5e00218bc07d0",
                "14485A9BA26B4BB2898D1B7AD7618DBC85bad7a1ea5f6a6936f68eadab2f816a",
                "176AC239D5054F08B573DD1F811880AEdc9240219f0a29a2cfccb4e904450504",
                "177BD52993B540B79770A3282BD9970Af5bcd6a29350dcbffd5231d7501db8c2",
                "186B661EFE36457586570D88C0C9934E434674dd9429577bc6cd36ed6fe74cc5",
                "18EF2974BA31498984CEC5795313BE7C5396f8faff32330dc8919c0469fc7c9a",
                "19AF4A04DD934B4CAA6DB9AD69CBA7FC741258fe5185c5705adab8623c2f7052",
                "1BFF035416774C8B8FF5121DFF33C2B12152f6b7bc291b7ce2d220c9bd9575b1",
                "1CD2D91776D94E8EB7842F714412E09F1b2dce38b9a751bfad40c87fd5e6a4ec",
                "1F6734F34F234D56A63373AD4845485E12c354ecff8a80f80fdaf782ebfa5d72");

        OppoUserDataVO build = OppoUserDataVO.builder()
                .type(2)
                .dataType(7)
                .channel(1)
                .payAmount(10000L)
                .timestamp(System.currentTimeMillis())
                .pkg("com.huayu.lianainiang")
                .ascribeType(1)
                .adId(447797271L)
                .appType(1)
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
