package com.xiaoxiong.bumblebee.chat.model;

import java.util.Set;

/**
 * @Author 六月
 * @Date 2023/6/28 11:37
 * @Version 1.0
 */
public class User {

    private Integer id;

    private String userCode;
    private String userName;
    private String passWord;
    private String nickName;
    private String headPortrait;
    /**
     * 用户对应的角色集合
     */
    private Set<Role> roles;

}
