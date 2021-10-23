package com.xl.designPattern.facade;

/**
 * @author liangliang
 * @date 2021/2/15 20:19
 * @e-mail 1640432919@qq.com
 */
public class TheaterLight {

    private static TheaterLight instance = new TheaterLight();

    public static TheaterLight getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" TheaterLight on");
    }

    public void off() {
        System.out.println(" TheaterLight off");
    }

    public void dim() {
        System.out.println(" TheaterLight dim");
    }

    public void bright() {
        System.out.println(" TheaterLight bright");
    }

}
