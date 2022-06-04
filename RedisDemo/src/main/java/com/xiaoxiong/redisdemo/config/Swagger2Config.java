package com.xiaoxiong.redisdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/19 12:56
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket redisGeo() {
        return new Docket(DocumentationType.SPRING_WEB)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xiaoxiong.redisdemo.case1.controller"))
                // .paths(PathSelectors.any())
                .paths(PathSelectors.ant("/case1/**"))
                .build()
                .groupName("geoDemo")
                .apiInfo(geoInfo());
    }

    @Bean
    public Docket redisString() {
        return new Docket(DocumentationType.SPRING_WEB)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xiaoxiong.redisdemo.case3"))
                .paths(PathSelectors.ant("/case3/**"))
                .build().groupName("redisPVOPS");
    }

    /**
     * redis用zset实现排行榜
     * @return
     */
    @Bean
    public Docket redisZSet() {
        return new Docket(DocumentationType.SPRING_WEB)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xiaoxiong.redisdemo.case4"))
                .paths(PathSelectors.ant("/case4/**"))
                .build().groupName("RankList");
    }

    private ApiInfo geoInfo() {
        return new ApiInfoBuilder().title("redis中geo类型使用案例")
                .description("geo类型使用案例").version("1.0").build();
    }

}
