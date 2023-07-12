package com.xiaoxiong.jdk;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author 六月
 * @Date 2023/7/11 17:56
 * @Version 1.0
 */
public class LocalDateTest {


    @Test
    public void diffDateTest() {
        long between = ChronoUnit.DAYS.between(Instant.ofEpochMilli(1689069453132L).atZone(ZoneId.systemDefault()).toLocalDate(),
                Instant.ofEpochMilli(1699069453932L).atZone(ZoneId.systemDefault()).toLocalDate());
        System.out.println(between);
    }

    @Test
    public void booleanTest() {
        String str = "{\"attrType\":\"selfAttr\",\"clickUrlVersion\":\"v3\",\"utmSource\":\"OCPD_OPPO_V3\",\"vestChannel\":\"nuanliao\",\"firstPay\":\"true\"}";
        Map<String, String> map = JSON.parseObject(str, Map.class);
        String firstPay = map.getOrDefault("firstPay", "false");
        Boolean aBoolean = Boolean.valueOf(firstPay);
        System.out.println(aBoolean);
    }

    @Test
    public void dateTest() {
        Date date1 = new Date();
        Date date2 = new Date(1689061734000L);
        long diffInMilliseconds = date1.getTime() - date2.getTime();
        long diffInDays = TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
        System.out.println(diffInDays);
    }

}
