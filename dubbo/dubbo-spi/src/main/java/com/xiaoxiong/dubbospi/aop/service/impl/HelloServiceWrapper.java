package com.xiaoxiong.dubbospi.aop.service.impl;

import com.xiaoxiong.dubbospi.aop.service.HelloService;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/22  22:23
 */
public class HelloServiceWrapper implements HelloService {

    private HelloService service;

    public HelloServiceWrapper(HelloService service) {
        this.service = service;
    }

    @Override
    public void sayHello() {
        System.out.println("before");
        service.sayHello();
        System.out.println("after");
    }
}
