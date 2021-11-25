package com.xiaoxiong.javaspi.service.impl;

import com.xiaoxiong.javaspi.service.HelloService;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/22  21:04
 */
public class MyHelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println(this.getClass().getName());
    }
}
