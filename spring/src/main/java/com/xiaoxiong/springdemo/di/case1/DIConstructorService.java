package com.xiaoxiong.springdemo.di.case1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/6/4  23:06
 */
public class DIConstructorService {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");
        ConstructorService2 constructorService2 = applicationContext.getBean("constructorService2", ConstructorService2.class);
    }

}
