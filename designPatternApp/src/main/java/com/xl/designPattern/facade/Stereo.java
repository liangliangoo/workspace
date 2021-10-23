package com.xl.designPattern.facade;

/**
 * @author liangliang
 * @date 2021/2/15 20:17
 * @e-mail 1640432919@qq.com
 */
public class Stereo {

    private static Stereo instance = new Stereo();

    public static Stereo getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" Stereo on");
    }

    public void off() {
        System.out.println(" Stereo off");
    }

    public void up() {
        System.out.println(" Stereo up");
    }

    public void down() {
        System.out.println(" Stereo down");
    }
}
