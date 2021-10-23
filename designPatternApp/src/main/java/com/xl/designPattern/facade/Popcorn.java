package com.xl.designPattern.facade;

/**
 * @author liangliang
 * @date 2021/2/15 20:11
 * @e-mail 1640432919@qq.com
 */
public class Popcorn {

    private static Popcorn instance = new Popcorn();

    public static Popcorn getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" Popcorn on");
    }

    public void off() {
        System.out.println(" Popcorn off");
    }

    public void pop() {
        System.out.println(" Popcorn is pop");
    }

}
