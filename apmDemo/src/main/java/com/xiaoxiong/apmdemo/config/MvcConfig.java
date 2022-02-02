package com.xiaoxiong.apmdemo.config;

import com.xiaoxiong.apmdemo.interceptor.MyMetricsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/25  13:41
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private MyMetricsInterceptor myMetricsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myMetricsInterceptor).addPathPatterns("/**");
    }

}
