package com.xiaoxiong.springmvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 六月
 * @Date 2022/8/12 15:00
 * @Version 1.0
 * @pathvariable 注解
 */
@Slf4j
@RestController
public class PathVariableController {

    @GetMapping({"/api/test/params/{type}", "/api/test/params/{type}/{name}"})
    public String pathParams(@PathVariable(value = "type") String type,
                             @PathVariable(value = "name", required = false) String name) {
        log.info("type:{},name:{}", type, name);
        return "hello";
    }

}
