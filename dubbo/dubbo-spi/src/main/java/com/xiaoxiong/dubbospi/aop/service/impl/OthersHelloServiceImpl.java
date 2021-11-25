package com.xiaoxiong.dubbospi.aop.service.impl;

import com.xiaoxiong.dubbospi.aop.service.HelloService;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/22  22:18
 */
public class OthersHelloServiceImpl implements HelloService {

    @Override
    public void sayHello() {
        System.out.println(this.getClass().getName());
    }

}
