package com.xiaoxiong.design.proxy.cglib;

/**
 * @author xiongliang
 * @version 1.0
 * @description student继承teacher
 * @since 2022/4/7  14:12
 */
public class Student extends Teacher{

    @Override
    public void sayHello() {
        System.out.println("I'm a student");
    }

    public final void student() {
        System.out.println("student");
    }

}
