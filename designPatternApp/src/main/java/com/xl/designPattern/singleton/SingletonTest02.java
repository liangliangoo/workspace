package com.xl.designPattern.singleton;

/**
 * @author liangliang
 * @date 2021/1/27 10:37
 * @e-mail 1640432919@qq.com
 */
public class SingletonTest02 {
}


//饿汉式（静态变量）
class Singleton1 {

    //构造器私有化（防止 new）
    private Singleton1() {

    }

    //类的内部创建对象
    private  static Singleton1 instance ;

    static {
        instance = new Singleton1();
    }

    //向外暴露一个静态的公共方法
    public static Singleton1 getInstance() {
        return instance;
    }

}