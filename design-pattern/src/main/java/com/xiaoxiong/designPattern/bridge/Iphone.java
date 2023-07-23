package com.xiaoxiong.designPattern.bridge;

/**
 * @author liangliang
 * @date 2021/2/9 10:17
 * @e-mail 1640432919@qq.com
 */
public class Iphone implements Brand {

    @Override
    public void open() {
        System.out.println("苹果手机开机");
    }

    @Override
    public void close() {
        System.out.println("苹果手机关机");
    }

    @Override
    public void call() {
        System.out.println("苹果手机打电话");
    }
}
