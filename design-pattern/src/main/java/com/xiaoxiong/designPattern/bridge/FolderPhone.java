package com.xiaoxiong.designPattern.bridge;

/**
 * @author liangliang
 * @date 2021/2/9 10:23
 * @e-mail 1640432919@qq.com
 */
public class FolderPhone extends Phone {

    public FolderPhone(Brand brand) {
        super(brand);
    }

    @Override
    protected void open() {
        super.open();
        System.out.println("折叠式手机打开");
    }

    @Override
    protected void close() {
        super.close();
        System.out.println("折叠式手机关机");
    }

    @Override
    protected void call() {
        super.call();
        System.out.println("折叠式手机打电话");
    }
}
