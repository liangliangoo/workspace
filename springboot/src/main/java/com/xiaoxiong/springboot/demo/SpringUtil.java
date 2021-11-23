package com.xiaoxiong.springboot.demo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/23 9:19
 */
@Component
public class SpringUtil implements ApplicationContextAware, EnvironmentAware {


    private static ApplicationContext applicationContext;
    private static Environment environment;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * Set the {@code Environment} that this component runs in.
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        SpringUtil.environment = environment;
    }

    public static  <T> T getBean(Class<T> clz) {
        return applicationContext.getBean(clz);
    }

    public static String getProperty(String Key) {
        return environment.getProperty(Key);
    }


}
