package com.xiaoxiong.jdk.map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

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



}
