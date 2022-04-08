package com.xiaoxiong.design.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author xiongliang
 * @version 1.0
 * @description 代理工厂
 * @since 2022/4/7  14:29
 */
public class CglibProxyFactory {

    public static Object newProxyInstance(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new CglibInterceptor());
        return enhancer.create();
    }

}
