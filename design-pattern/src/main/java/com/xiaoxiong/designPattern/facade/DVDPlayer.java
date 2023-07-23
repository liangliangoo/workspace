package com.xiaoxiong.designPattern.facade;

/**
 * @author liangliang
 * @date 2021/2/15 20:07
 * @e-mail 1640432919@qq.com
 */
public class DVDPlayer {

    private static DVDPlayer instance = new DVDPlayer();

    public static DVDPlayer getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" dvd on");
    }

    public void off() {
        System.out.println(" dvd off");
    }

    public void play() {
        System.out.println(" dvd is playing");
    }

    public void pause() {
        System.out.println(" dvd pause");
    }

}
