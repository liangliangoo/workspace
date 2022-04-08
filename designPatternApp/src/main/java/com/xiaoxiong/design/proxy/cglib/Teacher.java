package com.xiaoxiong.design.proxy.cglib;

/**
 * @author xiongliang
 * @version 1.0
 * @description 父类
 * @since 2022/4/7  14:10
 */
public class Teacher implements Person{


    @Override
    public void sayHello() {
        System.out.println("hello, I'm Miss Xiong");
    }

    @Override
    public void run() {
        System.out.println("car");
    }
}
