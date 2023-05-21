package com.xiaoxiong.opposdk;

import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;

import java.math.BigDecimal;
import java.nio.charset.Charset;

/**
 *
 * 广告主ID：1000185183
 * api- ID：0b5192625c794244b06e5f65263b81a1
 * api-key：00d2e9450ffa4518961b4bd28e115065
 *
 * @Author 六月
 * @Date 2023/5/16 18:17
 * @Version 1.0
 */
public class OppoToken {



    public static void main(String[] a) {
        int timestamp = unixTime();
        long ownerId = 1000185183;
        String apiId = "0b5192625c794244b06e5f65263b81a1";
        String apiKey = "00d2e9450ffa4518961b4bd28e115065";
        System.out.println(generateToken(apiId, apiKey, ownerId, timestamp));
    }
    private static String generateToken(String apiId, String apiKey, long ownerId, int timestamp) {

        String sign = Hashing.sha1().hashString(apiId + apiKey + timestamp, Charset.defaultCharset()).toString();

        String token = BaseEncoding.base64().encode((ownerId + "," + apiId + "," + timestamp + "," + sign).getBytes());

        return token;

    }


    public static int unixTime() {
        BigDecimal num = new BigDecimal(System.currentTimeMillis() / 1000L);
        return num.intValue();

    }

}
