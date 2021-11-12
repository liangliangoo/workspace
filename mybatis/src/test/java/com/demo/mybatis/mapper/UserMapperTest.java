package com.demo.mybatis.mapper;

import com.demo.mybatis.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

}
