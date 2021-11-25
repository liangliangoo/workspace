package com.xiaoxiong.dubbo.service.impl;

import com.xiaoxiong.commons.service.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/18  15:48
 */
@DubboService
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello() {
        return "hello dubbo";
    }

}
