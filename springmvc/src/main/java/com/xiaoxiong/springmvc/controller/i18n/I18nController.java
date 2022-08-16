package com.xiaoxiong.springmvc.controller.i18n;

import com.xiaoxiong.springmvc.common.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liuyue
 * @Date 2022/7/7 22:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/i18n")
public class I18nController {


    @GetMapping("/test")
    public Response<String> i18nTest() {
        return Response.success("message.classname", "18n");
    }

}
