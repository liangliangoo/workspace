package com.xiaoxiong.design.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.jupiter.api.Test;

/**
 * @author xiongliang
 * @version 1.0
 * @description 接口代理
 * @since 2022/4/7  15:18
 */
public class IPersonTest {

    @Test
    void test_interface() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            System.out.println("AOP");
            return null;
        });
        Person person = (Person) enhancer.create();
        person.sayHello();
    }

}
