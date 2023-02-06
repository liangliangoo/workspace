package com.xiaoxiong.dtsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2023/2/4  15:04
 */
@RefreshScope
@SpringBootApplication
public class DtpApplication {

    public static void main(String[] args) {
        SpringApplication.run(DtpApplication.class, args);
    }

}
