package com.xiaoxiong.branch;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @Author 六月
 * @Date 2023/3/23 20:02
 * @Version 1.0
 */
public class Testi {

    @Test
    void test1() {
        List<Index> indices = Lists.newArrayList();
        Long idx = 0L;
        for (int i = 100000000; i < 100000010; i++) {
            indices.add(new Index(++idx));
        }
        System.out.println(JSON.toJSON(indices));
    }

    @AllArgsConstructor
    @Data
    private static class Index {
        Long idx;
    }

}
