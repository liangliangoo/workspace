package com.xiaoxiong.redisdemo.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/17 14:50
 */
@SpringBootTest
public class RedisConfig_Test {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void select_test() {
        List position = redisTemplate.opsForGeo().position("HeNan", "XinYang");
        if (position.isEmpty()) {
            System.out.println(" no data");
        } else {
            System.out.println(position.get(0));
        }
    }

}
