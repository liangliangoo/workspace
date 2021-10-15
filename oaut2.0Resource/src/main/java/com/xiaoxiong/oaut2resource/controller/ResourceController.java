package com.xiaoxiong.oaut2resource.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/15  10:44
 */
@RestController
public class ResourceController {

    @Value("${xiaoxiong.username}")
    private String userName;

    @Value("${xiaoxiong.appsecret}")
    private String passwd;


    @RequestMapping("/myresource")
    public String test_source() {
        System.out.println("test");
        return "get Resource";
    }

}
