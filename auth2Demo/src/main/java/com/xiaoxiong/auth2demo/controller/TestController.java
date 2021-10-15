package com.xiaoxiong.auth2demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/14  16:45
 */
@RestController
public class TestController {

    @GetMapping("/callback")
    public String test_callback() {
        return "callback";
    }

}
