package com.xiaoxiong.rabbitmq.jdk;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/2/17 18:45
 */
public class TestBigDecimal {

    @Test
    public void test_bigDecimal() {
        BigDecimal bigDecimal1 = new BigDecimal(1);
        BigDecimal bigDecimal2 = new BigDecimal(1);
        System.out.println(bigDecimal1.equals(bigDecimal2));

        BigDecimal bigDecimal3 = new BigDecimal(1);
        BigDecimal bigDecimal4 = new BigDecimal(1.0);
        System.out.println(bigDecimal3.equals(bigDecimal4));

        BigDecimal bigDecimal5 = new BigDecimal("1");
        BigDecimal bigDecimal6 = new BigDecimal("1.0");
        BigDecimal bigDecimal7 = new BigDecimal("1.0000");
        System.out.println(bigDecimal5.equals(bigDecimal6));
    }

}
