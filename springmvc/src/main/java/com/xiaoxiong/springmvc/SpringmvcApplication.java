package com.xiaoxiong.springmvc;

import com.xiaoxiong.springmvc.component.mvc.ApiVersionHandlerMapping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * create by xiongliang
 */
@SpringBootApplication
public class SpringmvcApplication implements WebMvcRegistrations {


    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new ApiVersionHandlerMapping();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringmvcApplication.class, args);
    }

}
