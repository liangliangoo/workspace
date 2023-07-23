package com.xiaoxiong.designPattern.proxy.dynamicproxy;

import com.xiaoxiong.designPattern.proxy.dynamicproxy.factory.ProxyFactory;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/23  14:17
 */
public class DynamicProxyApplication {

    public static void main(String[] args) {
        Person proxyInstance = (Person) ProxyFactory.getProxyInstance(new Student());
        proxyInstance.student();
    }

}
