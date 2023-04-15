package com.xiaoxiong.jdk;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 六月
 * @Date 2023/4/13 19:49
 * @Version 1.0
 */
public class BigDecimalTest {

    @Test
    void test() {
        Double aDouble = new Double("99999599");
        System.out.println(aDouble);
        System.out.println(BigDecimal.valueOf(aDouble));
        System.out.println(Double.MAX_VALUE);
        System.out.println(Double.MAX_VALUE);
    }

    @Test
    void test_filter() {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 45, 56);
        List<Integer> collect = list.stream().filter(item -> item > 10).collect(Collectors.toList());
        System.out.println(collect);
    }

}
