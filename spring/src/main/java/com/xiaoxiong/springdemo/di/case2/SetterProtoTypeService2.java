package com.xiaoxiong.springdemo.di.case2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/6/4  22:55
 */
@Service
@Scope("prototype")
public class SetterProtoTypeService2 {

    @Autowired
    private SetterProtoTypeService1 setterProtoTypeService1;

    public void test2() {
        setterProtoTypeService1.hello();
    }

    public void hello() {
        System.out.println("hello SetterProtoTypeService2");
    }
}
