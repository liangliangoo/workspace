package com.xiaoxiong.springdemo.di.case2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/6/4  22:57
 */
public class DICase2Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");
        SetterProtoTypeService1 constService1 = applicationContext.getBean("constService1", SetterProtoTypeService1.class);
        constService1.test1();
    }

}
