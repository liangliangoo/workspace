package com.xiaoxiong.springmvc.config.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * api文档相关配置
 * 1、实现ApplicationListener的目的，是保证项目启动后，能够看到文档地址链接，方便访问
 * 2、swagger只能在开发测试环境生效，配置文件中需要提供一个开关
 * 1、方案一：通过pom.xml中的profile标签控制，这用方式方便简洁，案例中采用了这用形式
 * 2、@ConditionalOnProperty 在配置文件中添加一个属性控制，这个注解可以控制@Configuration是否生效解决
 *
 * @Author xiongliang
 * @Date 2022/7/9 16:36
 * @Version 1.0
 */
@Slf4j
@Profile("dev")
@EnableSwagger2
@Configuration
//@ConditionalOnProperty(value = "swagger2.enable",havingValue = "true") // 也可以通过这种方式配置一个swagger开关
public class Swagger2Config implements ApplicationListener<WebServerInitializedEvent> {

    @Resource
    private Environment environment;

    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("i8n") // 文档的分组名称
                .apiInfo(webApiInfo())
                .select()
                .paths(PathSelectors.regex("/i18n/.*"))
                .build();
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("spring-mvc-application") // 文档的题头
                .description("Api文档，方便调试") //文档的描述
                .version("1.0")
                .build();
    }

    /**
     * {@inheritDoc}
     *
     * @param webServerInitializedEvent
     */
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        try {
            //获取IP
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            //获取端口号
            int port = event.getWebServer().getPort();
            //获取应用名
            String applicationName = environment.getProperty("spring.application.name");
            log.info("应用名：", applicationName);
            log.info("项目启动启动成功！接口文档地址: \t\t http://" + hostAddress + ":" + port + "/swagger-ui.html");
        } catch (UnknownHostException e) {
            log.error("Inet4Address.getLocalHost() error,cased by:{}", e.getMessage());
        }
    }
}
