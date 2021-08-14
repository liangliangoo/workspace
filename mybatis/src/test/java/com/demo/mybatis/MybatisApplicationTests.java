package com.demo.mybatis;

import com.demo.mybatis.entity.User;
import com.demo.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(userMapper);
    }

    @Test
    public void insertTest(){
        User user = new User();

        for (int i = 2; i < 1000_0000; i++) {
            user.setId(i).setAge(i).setName("name" + i);
            userMapper.insert(user);
        }

    }

    @Test
    public void selectTest(){
        List<User> users = userMapper.listAll();
        System.out.println(users.size());
    }

    @Test
    public void selectOneTest(){
        userMapper.findById(111);
    }

}
