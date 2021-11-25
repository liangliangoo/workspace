package com.xiaoxiong.javaspi;

import com.xiaoxiong.javaspi.service.HelloService;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * java spi demo
 *
 * Mysql 等等数据库驱动都是采用的javaSPI实现的
 *
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/22  21:06
 */
public class DemoApplication {

    public static void main(String[] args) {

        /*
        java SPI 不能获取指定的实现类
        而且没有IOC 和 AOP
         */

        ServiceLoader<HelloService> load = ServiceLoader.load(HelloService.class);
        Iterator<HelloService> iterator = load.iterator();
        while (iterator.hasNext()) {
            iterator.next().sayHello();
        }
    }

}
