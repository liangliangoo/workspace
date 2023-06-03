package com.xiaoxiong.learning.java8.effecticejava;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/24  11:51
 */
public class DemoFinal {

    // 这样定义存在安全漏洞,解决方案如下
    public static final String[] VALUES = {"a", "b"};

    public static void test() {
        VALUES[1] = "a";
    }

    // solution 1
    private static final String[] VALUES_SOLUTION_1 = {"a", "b"};
    public static final List<String> VALUES_LIST = Collections.unmodifiableList(Arrays.asList(VALUES_SOLUTION_1));

    /**
     * java.lang.UnsupportedOperationException
     */
    public static void solution_1_test() {
        VALUES_LIST.add("a");
    }


    // solution 2
    private static final String[] VALUES_SOLUTION_2 = {"A", "B"};

    public static final String[] getValues() {
        return VALUES_SOLUTION_2.clone();
    }


    public static void main(String[] args) {
        solution_1_test();
    }

}
