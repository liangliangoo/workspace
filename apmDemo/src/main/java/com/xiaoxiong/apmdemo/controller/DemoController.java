package com.xiaoxiong.apmdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/25  13:45
 */
@RestController
public class DemoController {

    private Random random = new Random();

    @GetMapping(path = "hello")
    public String hello(String name) {
        int sleep = random.nextInt(200);
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello sleep: " + sleep + " for " + name;
    }

}
