package com.xiaoxiong.dubboconsumer.controller;

import com.xiaoxiong.commons.service.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/18  17:22
 */
@RestController
@RequestMapping("/dubbo")
public class DubboTestController {

    @DubboReference
    private HelloService helloService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/hello")
    public String sayHello() {
        return helloService.sayHello();
    }

}
