package com.xiaoxiong.activity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author 六月
 * @Date 2022/9/4 21:55
 * @Version 1.0
 * 活动相关业务中台
 */
@Slf4j
@SpringBootApplication
public class ActivityApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ActivityApplication.class, args);
        System.out.println("--------------ActivityApplication 启动成功 ------------------");
    }

}
