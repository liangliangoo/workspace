package com.xiaoxiong.springdemo;

import com.xiaoxiong.springdemo.component.MyEventPublisher;
import com.xiaoxiong.springdemo.event.StringEvent;
import com.xiaoxiong.springdemo.pojo.User;
import com.xiaoxiong.springdemo.service.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/8  15:39
 */
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        System.out.println(userDao.findAll().toArray().toString());
        User user = userDao.findById(1);

        MyEventPublisher publisher = applicationContext.getBean("myEventPublisher", MyEventPublisher.class);
        publisher.publishEvent(new StringEvent("------------- myEventPublisher publish ---------------------"));

    }

}
