package com.xiaoxiong.feign.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 六月
 * @Date 2022/8/15 15:40
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/feign")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello feign";
    }

}
