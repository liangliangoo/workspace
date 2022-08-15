package com.xiaoxiong.feign.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 六月
 * @Date 2022/8/15 15:47
 * @Version 1.0
 */
@FeignClient(name = "feign-services")
public interface HelloService {

    @GetMapping("/api/feign/hello")
    String hello();

}
