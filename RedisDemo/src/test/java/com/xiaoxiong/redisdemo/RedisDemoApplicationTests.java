package com.xiaoxiong.redisdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;

@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        BigDecimal decimal1 = new BigDecimal(String.valueOf(redisTemplate.opsForHash().get("test_hash", "100")));
        BigDecimal decimal2 = new BigDecimal(String.valueOf(redisTemplate.opsForHash().get("test_hash", "101")));
        System.out.println(decimal1);
        System.out.println(decimal2);

    }

}
