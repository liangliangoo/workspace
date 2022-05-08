package com.xiaoxiong.mockdata.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/8  14:40
 */
public class StaticUtils {

    private StaticUtils() {

    }

    public static List<Integer> range(int start, int end) {
        return IntStream.range(start,end).boxed().collect(Collectors.toList());
    }

    public static String name() {
        return "Echo";
    }

}
