package com.xiaoxiong.jdk;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @Author 六月
 * @Date 2023/3/30 16:56
 * @Version 1.0
 */
public class ArrayListTest {

    @Test
    void testArrayList() {
        ArrayList<Integer> stakeList = Lists.newArrayList(null, 2, 3, 4, 5, null, 7, 8);
        for (int i = 0; i < stakeList.size(); i++) {
            if (Objects.isNull(stakeList.get(i))) {
                stakeList.set(i, 0);
            }
        }
        System.out.println(JSON.toJSONString(stakeList));
    }

}
