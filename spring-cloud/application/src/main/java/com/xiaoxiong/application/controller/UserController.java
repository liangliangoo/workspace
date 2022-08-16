package com.xiaoxiong.application.controller;

import com.xiaoxiong.common.dubbo.UserManager;
import com.xiaoxiong.common.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 六月
 * @Date 2022/8/14 14:13
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @DubboReference
    private UserManager userManager;

    @GetMapping("/getUserByID")
    public ResponseEntity<UserVo> getUserByID(@RequestParam Integer id) {
        UserVo result = userManager.getUserByID(id);
        return ResponseEntity.ok(result);
    }

}
