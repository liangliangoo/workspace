package com.xiaoxiong.springmvc.controller.versions;

import com.xiaoxiong.springmvc.common.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/7/5 20:55
 */
@RestController
@RequestMapping("/api/version")
public class ApiController {

    @GetMapping("testVersion")
    public Response<String> version() {
        return Response.success("version");
    }

}
