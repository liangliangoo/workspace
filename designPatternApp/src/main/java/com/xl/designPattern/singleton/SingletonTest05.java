package com.xl.designPattern.singleton;

/**
 * @author liangliang
 * @date 2021/1/27 11:04
 * @e-mail 1640432919@qq.com
 */
public class SingletonTest05 {

    public static void main(String[] args) {
        System.out.println(Singleton05.getInstance() == Singleton05.getInstance());
    }

}

class Singleton05 {

    private Singleton05() {

    }

    private static volatile Singleton05 instance;

    public static Singleton05 getInstance() {
        if (instance == null) {
            synchronized (Singleton05.class) {
                if (instance == null) {
                    instance = new Singleton05();
                }
            }
        }
        return instance;
    }

}
