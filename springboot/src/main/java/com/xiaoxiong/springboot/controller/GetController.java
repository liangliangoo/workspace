package com.xiaoxiong.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 六月
 * @Date 2023/3/19 16:38
 * @Version 1.0
 */
@Slf4j
@RequestMapping("/api/spring-boot/get/test")
@RestController
public class GetController {

    @GetMapping("/get-params")
    public String getParams(Long appId, Long userId, String status) {
        log.info("getParams appId {} userId {} status {}", appId, userId, status);
        if (appId != null) {
            log.info("appId is not null");
        }
        if (userId != null) {
            log.info("userId is not null");
        }
        // http://127.0.0.1:9091/api/spring-boot/get/test/get-params?appId=&userId=&status=
        // 如果参数是string 类型  服务端看到的status="" 空的串但是不是空的对象
        if (!StringUtils.isEmpty(status)) {
            log.info("status is not null");
        }
        return "get 请求参数为空写与不写的区别";
    }

}
