package com.xiaoxiong.learning.spi.impl;

import com.xiaoxiong.learning.spi.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/9  10:56
 */
public class UserServiceImpl implements UserService {

    private static final List<User> LIST;

    static {
        LIST = new ArrayList<User>();
        LIST.add(new User("1", 12));
        LIST.add(new User("2", 23));
    }

    public List<User> findAllUser() {
        System.out.println("UserServiceImpl.findAllUser");
        return LIST;
    }

}
