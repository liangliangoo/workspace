package com.xiaoxiong.design.strategy.service.impl;

import com.xiaoxiong.design.strategy.service.IService;
import com.xiaoxiong.design.strategy.User;

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
