package com.xiaoxiong.designPattern.facade;

/**
 * @author liangliang
 * @date 2021/2/15 20:13
 * @e-mail 1640432919@qq.com
 */
public class Projector {

    private static Projector instance = new Projector();

    public static Projector getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" Projector on");
    }

    public void off() {
        System.out.println(" Projector off");
    }

    public void focus() {
        System.out.println(" Projector is focus");
    }

}
