package com.xiaoxiong.feign.controller;

import com.xiaoxiong.feign.remote.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author 六月
 * @Date 2022/8/15 15:49
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/app")
public class HelloController {

    @Resource
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        return helloService.hello();
    }

}
