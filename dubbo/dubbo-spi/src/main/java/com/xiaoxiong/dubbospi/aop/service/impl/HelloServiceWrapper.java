package com.xiaoxiong.dubbospi.aop.service.impl;

import com.xiaoxiong.dubbospi.aop.service.HelloService;

/**
 * 这个包装类就是dubbo spi AOP的实现
 * 但是这个包装类必须在 /META-INF/services/com.xiaoxiong.dubbospi.aop.service.HelloService中说明
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
