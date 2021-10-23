package com.xl.designPattern.description;

/**
 * @author liangliang
 * @date 2021/2/13 19:45
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {
        Drink order = new LongBlack();
        System.out.println(order.cost());
        order = new Milk(order);
        System.out.println(order.cost() + order.getDescription());
    }

}
