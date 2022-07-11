//package com.demo.mybatis.service.impl;
//
//import com.demo.mybatis.entity.User;
//import com.demo.mybatis.service.UserPlusService;
//import org.assertj.core.util.Lists;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//
//import java.util.ArrayList;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//public class UserPlusServiceImplTest {
//
//
//    @Resource
//    private UserPlusServiceImpl userPlusService;
//
//    /**
//     * mybatis-plus 提供的批量插入，底层是调用ExecutorType.BATCH 的逻辑
//     */
//    @Test
//    public void test_batchInsert() {
//        ArrayList<User> users = Lists.newArrayList();
//        for (int i = 0; i < 100; i++) {
//            User user = new User().setAge(18 + i % 18 + 10).setEmail("liuyue" + i + 1000 + "qq.com").setName("六月" + i + 1000);
//            users.add(user);
//        }
//        userPlusService.saveBatch(users);
//    }
//
//}