package com.xl.designPattern.singleton;

/**
 * @author liangliang
 * @date 2021/1/27 10:56
 * @e-mail 1640432919@qq.com
 */
public class SingletonTest04 {
}
class Singleton04 {

    private static Singleton04 instance;

    private Singleton04 () {

    }

    public static synchronized Singleton04 getInstance() {
        if (instance == null){
            instance = new Singleton04();
        }
        return instance;
    }
}