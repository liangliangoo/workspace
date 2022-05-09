package com.xiaoxiong.learning.spi;

import java.util.ServiceLoader;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/9  11:01
 */
public class SpiDemo {

    public static void main(String[] args) {
        ServiceLoader<UserService> loader = ServiceLoader.load(UserService.class);
        UserService userService = loader.iterator().next();
        System.out.println(userService.findAllUser());
    }

}
