package com.xiaoxiong.designPattern.bridge;

/**
 * @author liangliang
 * @date 2021/2/9 10:18
 * @e-mail 1640432919@qq.com
 */
public abstract class Phone {

    private Brand brand;

    public Phone(Brand brand) {
        this.brand = brand;
    }

    protected void open() {
        this.brand.open();
    }

    protected void close() {
        this.brand.close();
    }

    protected void call() {
        this.brand.call();
    }
}
