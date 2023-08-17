package com.xiaoxiong.stream;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 六月
 * @Date 2023/8/15 20:06
 * @Version 1.0
 */
public class StreamFilter {

    public static void main(String[] args) {
        List<Integer> collect = Lists.newArrayList(1, 2, 3, 4, 5, 67, 78, 8).stream().filter(item -> item > 8).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
        System.out.println(1692028800000L - 1691510400000L);
    }

}
