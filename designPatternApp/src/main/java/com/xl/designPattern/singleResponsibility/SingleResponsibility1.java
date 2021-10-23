package com.xl.designPattern.singleResponsibility;

/**
 * @author liangliang
 * @date 2021/1/25 15:58
 * @e-mail 1640432919@qq.com
 */
public class SingleResponsibility1 {

    public static void main(String[] args) {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.run("摩托车");
        vehicle1.run("小汽车");
        vehicle1.run("飞机");

    }
}

//交通工具类
class Vehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + "在公路上跑");
        int i = 1;

    }
}
