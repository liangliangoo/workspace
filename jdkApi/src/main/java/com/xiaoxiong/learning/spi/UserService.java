package com.xiaoxiong.learning.spi;

import java.util.List;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/9  10:54
 */
public interface UserService {

    List<User> findAllUser();

    class User {
        private String name;
        private Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }


}

