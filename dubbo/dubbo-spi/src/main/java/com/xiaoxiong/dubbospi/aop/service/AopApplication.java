package com.xiaoxiong.dubbospi.aop.service;

import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/22  22:23
 */
public class AopApplication {

    public static void main(String[] args) {
        ExtensionLoader<HelloService> extensionLoader = ExtensionLoader.getExtensionLoader(HelloService.class);
        HelloService myService = extensionLoader.getExtension("myService");
        myService.sayHello();
    }

}
