package com.xiaoxiong.dubbospi.aop.service;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/22  22:17
 */
@SPI
public interface HelloService {

    void sayHello();

}
