package com.xiaoxiong.designPattern.bridge;

/**
 * @author liangliang
 * @date 2021/2/9 10:31
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {
        Phone phone1 = new FolderPhone(new Iphone());
        phone1.call();
    }
}
