package com.xiaoxiong.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author 六月
 * @Date 2022/8/15 15:23
 * @Version 1.0
 */
@Slf4j
@EnableFeignClients(basePackages = "com.xiaoxiong.*")
@EnableDiscoveryClient
@SpringBootApplication
public class FeignServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignServicesApplication.class, args);
    }

}
