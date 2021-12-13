package com.xiaoxiong.springsecuritydemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/12/11  11:05
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void loadUserByUsername_test() {
        System.out.println(userService.loadUserByUsername("xiaoxiong"));
    }

}
