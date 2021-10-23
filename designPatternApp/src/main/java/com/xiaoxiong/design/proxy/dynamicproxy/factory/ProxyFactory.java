package com.xiaoxiong.design.proxy.dynamicproxy.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/23  16:36
 */
public class ProxyFactory {

    @SuppressWarnings("unchecked")
    public static Object getProxyInstance(Object target) {

        return  Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return method.invoke(target,args);
                    }

                });

    }
}
