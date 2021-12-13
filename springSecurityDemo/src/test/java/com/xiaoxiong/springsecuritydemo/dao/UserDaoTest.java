package com.xiaoxiong.springsecuritydemo.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/12/11  10:54
 */
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void loadUserByUsername_test() {
        System.out.println(userDao.loadUserByUsername("xiaoxiong"));
    }

}
