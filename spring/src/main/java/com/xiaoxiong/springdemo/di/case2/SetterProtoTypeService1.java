package com.xiaoxiong.springdemo.di.case2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/6/4  22:54
 */
@Service
@Scope("prototype")
public class SetterProtoTypeService1 {

    @Autowired
    private SetterProtoTypeService2 setterProtoTypeService2;

    public void test1() {
        setterProtoTypeService2.hello();
    }

    public void hello() {
        System.out.println("hello SetterProtoTypeService1");
    }


}
