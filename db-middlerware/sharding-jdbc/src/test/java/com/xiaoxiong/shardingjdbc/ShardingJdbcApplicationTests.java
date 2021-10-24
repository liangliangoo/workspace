package com.xiaoxiong.shardingjdbc;

import com.xiaoxiong.shardingjdbc.mapper.UserMapper;
import com.xiaoxiong.shardingjdbc.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShardingJdbcApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test_sharding_select() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users.size());
    }

    @Test
    public void test_sharding_insert() {
        User user = new User();
        user.setAge(18);
        user.setName("小蔣");
        userMapper.insert(user);
    }

}
