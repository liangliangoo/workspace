package com.xiaoxiong.hbase.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/23  15:43
 */
public class CollectTest {

    @Test
    public void test_collect() {
        String[] strs = {"1", "2"};
        ArrayList<Integer> collect = Arrays.stream(strs).collect(() -> new ArrayList<Integer>(), (list, str) -> {
            list.add(Integer.valueOf(str));
        }, (list,a)->{
            list.addAll(a);
        });
        System.out.println(collect);
    }

}
