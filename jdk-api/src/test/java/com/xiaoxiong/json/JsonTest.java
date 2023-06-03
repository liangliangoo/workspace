package com.xiaoxiong.json;

import com.alibaba.fastjson2.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

/**
 * json 测试
 * @Author 六月
 * @Date 2023/4/3 15:19
 * @Version 1.0
 */
public class JsonTest {

    @Test
    void test_toJsonStr() {
        String str1 = "";
        String str2 = null;
        Map map1 = JSONObject.parseObject(str1, Map.class);
        Map map2 = JSONObject.parseObject(str2, Map.class);

    }

    @Test
    void test1() {
        Map<String, Object> map = JSONObject.parseObject("", Map.class);
        System.out.println(Optional.ofNullable((String) map.get("scene")).orElse("ACTIVITY_DEFAULT"));
    }

}
