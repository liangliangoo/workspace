package com.xiaoxiong.springboot;

import org.junit.jupiter.api.Test;

/**
 * @Author 六月
 * @Date 2023/4/25 18:29
 * @Version 1.0
 */
public class TestString {

    @Test
    public void test1() {

    }

    @Test
    void dealWith() {
        String s = "  954862, 858874    ,1121   ";
        String s1 = s.replace(" ", "");
        System.out.println(s1);
    }

}
