package com.xiaoxiong.springdemo.service.impl;

import com.xiaoxiong.springdemo.pojo.User;
import com.xiaoxiong.springdemo.service.UserDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/8  15:42
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private List<User> ans = new ArrayList<>();

    {
        ans.add(new User(1,20,"小熊"));
        ans.add(new User(2, 18, "小明"));
    }

    public List<User> findAll() {
        return ans;
    }

    @Override
    public User findById(Integer id) {
        for (User user : ans) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
