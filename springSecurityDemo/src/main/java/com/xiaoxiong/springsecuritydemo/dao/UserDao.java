package com.xiaoxiong.springsecuritydemo.dao;

import com.xiaoxiong.springsecuritydemo.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/12/11  10:45
 */
public interface UserDao {

    User loadUserByUsername(@Param("username") String userName);

}
