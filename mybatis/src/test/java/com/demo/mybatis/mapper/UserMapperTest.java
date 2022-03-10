package com.demo.mybatis.mapper;

import com.demo.mybatis.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/11  21:18
 */
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test_listAll() {
        List<User> users = userMapper.listAll();
        System.out.println(users);
        userMapper.listAll();
    }

    @Test
    public void test_insertUser() {
        User user = new User();
        String namPrefix = "小熊-";
        String emailPrefix = "xiaoxiong";
        String emailSuffix = "@gmail.com";
        Random random = new Random();
        for (int i = 1; i < 10_0000; i++) {
            user.setName(namPrefix + i).setAge(random.nextInt(80) + 1).setEmail(emailPrefix + i + emailSuffix).setId(i);
            userMapper.insert(user);
        }
    }

}
