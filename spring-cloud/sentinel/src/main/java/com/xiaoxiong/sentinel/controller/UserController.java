package com.xiaoxiong.sentinel.controller;

import com.xiaoxiong.sentinel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 六月
 * @Date 2022/8/6 16:53
 * @Version 1.0
 * 测试接口
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List> getAllUser() {
        return ResponseEntity.ok(userService.findAllUser());
    }

}
