package com.xl.designPattern.singleton;

/**
 * @author liangliang
 * @date 2021/1/27 10:46
 * @e-mail 1640432919@qq.com
 */
public class SingletonTest03 {
    public static void main(String[] args) {
        System.out.println(Singleton03.getInstance() == Singleton03.getInstance());
    }
}

class Singleton03 {

    private static Singleton03 instance;

    private Singleton03 () {

    }

    public static Singleton03 getInstance() {
        if (instance == null){
            instance = new Singleton03();
        }
        return instance;
    }
}
