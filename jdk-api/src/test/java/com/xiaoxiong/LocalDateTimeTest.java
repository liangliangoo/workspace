package com.xiaoxiong;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author 六月
 * @Date 2023/4/6 12:27
 * @Version 1.0
 */
public class LocalDateTimeTest {

    @Test
    void test_isAfter() {
        LocalDateTime parse = LocalDateTime.parse("2023-04-01T20:00:00");
        System.out.println(parse.isAfter(LocalDateTime.now()));
    }

    @Test
    public void test1() {
        System.out.println(BigDecimal.valueOf(100).divide(BigDecimal.valueOf(10)));
    }

}
