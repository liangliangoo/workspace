package com.xiaoxiong.auth2demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.RequestMapping;
>>>>>>> origin/feature/oauth2.0-2021-10-10
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/14  16:45
 */
@RestController
<<<<<<< HEAD
=======
@RequestMapping("/")
>>>>>>> origin/feature/oauth2.0-2021-10-10
public class TestController {

    @GetMapping("/callback")
    public String test_callback() {
        return "callback";
    }

}
