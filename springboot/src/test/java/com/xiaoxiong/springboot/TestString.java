package com.xiaoxiong.springboot;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

/**
 * @Author 六月
 * @Date 2023/4/25 18:29
 * @Version 1.0
 */
public class TestString {

    @Test
    public void test1() {

    }

    @Test
    void dealWith() {
        String s = "  954862, 858874    ,1121   ";
        String s1 = s.replace(" ", "");
        System.out.println(s1);
    }

    @Test
    public void test_beanCopy() {
        User2 user2 = new User2();
        User1 user1 = new User1("1", 1L);
        BeanUtils.copyProperties(user1, user2);
        System.out.println(JSON.toJSONString(user2));

        User2 user21 = JSON.parseObject(JSON.toJSONString(user1), User2.class);
        System.out.println(JSON.toJSONString(user21));


        BeanUtil.copyProperties(user1, user2);
        System.out.println(user2);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    class User1 {
        public String id;
        public Long age;
    }

    @Data
    class User2 {
        public Long id;
        public String age;
    }

}
