package com.xiaoxiong.jdk;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

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

}
