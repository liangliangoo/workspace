package com.xiaoxiong.designPattern.proxy.dynamicproxy;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/23  14:16
 */
public class Student implements Person{

    public String student(){
        System.out.println("学习");
        return "学习";
    }

}
