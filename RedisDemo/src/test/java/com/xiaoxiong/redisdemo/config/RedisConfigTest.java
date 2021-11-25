package com.xiaoxiong.redisdemo.config;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/13  20:16
 */
@SpringBootTest
public class RedisConfigTest {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void test_redisCacheTemplate() {
        Object test = redisTemplate.opsForValue().get("test");
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(test);
        System.out.println(jsonObject);
    }

    @Test
    public void test_put() {
        redisTemplate.opsForValue().set("redisTemplate", "test");
    }

}
