package com.xiaoxiong.jdk.map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 六月
 * @Date 2023/6/6 18:30
 * @Version 1.0
 */
public class MapTest {

    @Test
    public void test_putAll() {
        HashMap<String, Integer> result1 = Maps.newHashMap();
        result1.put("1", 111);
        HashMap<String, Integer> result2 = Maps.newHashMap();
        result2.put("1", 222);
        result1.putAll(result2);
        System.out.println(JSON.toJSONString(result1));
    }

    @Test
    public void test_exception() {
        try {
            throw new NullPointerException();
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
        }
    }

    @Test
    public void test_toMap() {
        String s = "{\"Gemini\":17777}";
        HashMap<String, Long> hashMap = JSON.parseObject(s, HashMap.class);
        for (Map.Entry<String, Long> entry : hashMap.entrySet()) {
            String s1 = entry.getValue().toString();
            System.out.println(s1);
            long uid = Long.parseLong(s1);
            System.out.println(uid);
        }
    }

    @Test
    public void test() {
        String s = "{\"srcId\":\"ds-202305252172\",\"custom\":\"nlcpc2023\",\"pkgName\":\"com.xiaoyu.lanling\",\"advertiserId\":\"824054f768a63a49297b\",\"token\":\"cb4d0d8547d2a94e91da5353c0c27de3ec47ce0a4e6fd0df7ce56dd58806ff27\",\"supportMediumDataCollector\":\"true\",\"attrType\":\"selfAttr\",\"vestChannel\":\"nuanliao\",\"utmSource\":\"OCPD_VIVO_V2\",\"clickUrlVersion\":\"v2\",\"oaidIsEncodeByMd5\":true}";
        if (StringUtils.isBlank(s)) {
            System.out.println(Boolean.FALSE);
        }
        JSONObject jsonObject = JSON.parseObject(s);
        System.out.println(jsonObject.getBoolean("oaidIsEncodeByMd5"));

    }



}
