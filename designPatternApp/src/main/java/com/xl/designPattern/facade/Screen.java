package com.xl.designPattern.facade;

/**
 * @author liangliang
 * @date 2021/2/15 20:15
 * @e-mail 1640432919@qq.com
 */
public class Screen {

    private static Screen instance = new Screen();

    public static Screen getInstance() {
        return instance;
    }

    public void up() {
        System.out.println(" Screen up");
    }

    public void down() {
        System.out.println(" Screen down");
    }

}
