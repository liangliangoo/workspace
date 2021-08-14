package com.demo.mybatis;

import com.demo.mybatis.entity.User;
import com.demo.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

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


    @Test
    public void cach1_test(){
        System.out.println("验证一级缓存存在的BUG");

        User user = userMapper.findById(1000);
        System.out.println(user.toString());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        user = userMapper.findById(1000);
        System.out.println(user.toString());
    }

}
