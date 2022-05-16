package com.xiaoxiong.webfluxclient;

import com.xiaoxiong.webfluxclient.interfaces.IUserApi;
import com.xiaoxiong.webfluxclient.proxy.ProxyCreator;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/11  19:28
 */
@SpringBootApplication
public class WebfluxClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxClientApplication.class, args);
    }

    @Bean
    FactoryBean<IUserApi> userApi(ProxyCreator proxyCreator) {
        return new FactoryBean<IUserApi>() {
            @Override
            public IUserApi getObject() throws Exception {
                return ((IUserApi) proxyCreator.createProxy(getObjectType()));
            }

            @Override
            public Class<?> getObjectType() {
                return IUserApi.class;
            }
        };
    }

}
