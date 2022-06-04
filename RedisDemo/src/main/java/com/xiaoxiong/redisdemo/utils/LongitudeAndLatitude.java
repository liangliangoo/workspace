package com.xiaoxiong.redisdemo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxiong.redisdemo.constant.GaoDeMap;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.data.geo.Point;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/18 10:32
 */
@Slf4j
public class LongitudeAndLatitude {

    public static String getLngAndLag(String address) throws UnsupportedEncodingException {
        address = address.trim();
        String url = GaoDeMap.URL + URLEncoder.encode(address, "utf-8") +
                "&output=JSON" + "&key=" + GaoDeMap.KEY;

        try {
            URL url2 = new URL(url);    // 把字符串转换为URL请求地址
            HttpURLConnection connection = (HttpURLConnection) url2.openConnection();// 打开连接
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            System.out.println("sb==" + sb.toString());
            JSONObject a = JSON.parseObject(sb.toString());
            JSONArray addressArr = JSON.parseArray(a.get("geocodes").toString());
            JSONObject c = JSON.parseObject(addressArr.get(0).toString());
            String location = c.get("location").toString();
            return location;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败!");
        }
        return null;
    }

    public static Point getLongitudeAndLatitude(String address) throws UnsupportedEncodingException {

        Point ans = null;
        String url = GaoDeMap.URL + URLEncoder.encode(address.trim(), "utf-8") +
                "&output=JSON" + "&key=" + GaoDeMap.KEY;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response execute = client.newCall(request).execute();
            ResponseBody body = execute.body();
            String str = body.source().readUtf8();
            JSONObject jsonObject = JSON.parseObject(str);
            JSONArray geocodes = JSON.parseArray(jsonObject.get("geocodes").toString());
            String location = JSON.parseObject(geocodes.getString(0).toString()).get("location").toString();
            if (StringUtils.isEmpty(location)) {
                throw new NullPointerException("location is null");
            } else {
                String[] split = location.split(",");
                ans = new Point(Double.valueOf(split[0]), Double.valueOf(split[1]));
            }
        } catch (IOException e) {
            log.error("request Error on " + url + ",caused by {}", e.getMessage());
        }
        return ans;
    }

}
