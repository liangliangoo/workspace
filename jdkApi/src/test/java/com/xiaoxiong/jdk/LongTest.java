package com.xiaoxiong.jdk;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;

/**
 * @Author 六月
 * @Date 2023/4/3 15:31
 * @Version 1.0
 */
public class LongTest {

    @Test
    void testLong() {
        Long userId1 = 954354L;
        Long userId2 = new Long(954354);
        Long userId3 = new Long(954354);
        System.out.println(userId1 == userId2);
        System.out.println(userId2 == userId3);
    }

    @Test
    void testUser() {
        User user = new User();
        System.out.println(JSON.toJSONString(user));
        User build = User.builder().userName("build").build();
        System.out.println(JSON.toJSONString(build));
    }

}
