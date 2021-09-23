package com.xiaoxiong.mockdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaoxiong.mockdata.dao")
public class MockDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockDataApplication.class, args);
    }

}
