package com.xiaoxiong.springboot.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/23 9:38
 */
@RestController
@RequestMapping("basicDemo")
public class TestController {

    @Autowired
    private BasicDemo basicDemo;

    @GetMapping("show")
    public String test_show(String data) {
        return basicDemo.process(data);
    }

}