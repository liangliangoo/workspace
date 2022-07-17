package com.xiaoxiong.job;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableConfigurationProperties
@SpringBootApplication
public class JobApplication {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
        System.in.read();
    }

}
