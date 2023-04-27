package com.xiaoxiong.springboot.controller;

import com.alibaba.fastjson2.JSON;
import com.xiaoxiong.springboot.param.ParamBind;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 参数绑定处理
 * @Author 六月
 * @Date 2023/4/26 10:46
 * @Version 1.0
 */
@Slf4j
@RestController
public class ParamBindController {

    @Resource
    private ApplicationContext applicationContext;

    @GetMapping("/api/mvc/param-bind-test")
    public String paramBind(ParamBind paramBind) {
        HttpMessageConverters bean = applicationContext.getBean(HttpMessageConverters.class);
        System.out.println("");
        log.info("paramBind {}", JSON.toJSON(paramBind));
        return JSON.toJSONString(paramBind);
    }

}
