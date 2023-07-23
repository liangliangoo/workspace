package com.xiaoxiong.designPattern.strategy.service.impl;


import com.xiaoxiong.designPattern.strategy.User;
import com.xiaoxiong.designPattern.strategy.service.IService;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/22  20:38
 */
public class AServiceImpl implements IService {

    @Override
    public void login(User user) {
        System.out.println("hello");
    }

}
