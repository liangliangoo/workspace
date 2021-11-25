package com.xiaoxiong.springboot.demo;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/23 9:27
 */
@Component
public class TestDemo {

    public String showCase() {
        return UUID.randomUUID().toString();
    }

    public String testCase() {
        return "test" + Math.random();
    }

}
