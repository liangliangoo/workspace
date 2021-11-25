package com.xiaoxiong.springdemo.service;

import com.xiaoxiong.springdemo.pojo.User;

import java.util.List;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/8  15:39
 */
public interface UserDao {

    List<User> findAll();

    User findById(Integer id);

}
