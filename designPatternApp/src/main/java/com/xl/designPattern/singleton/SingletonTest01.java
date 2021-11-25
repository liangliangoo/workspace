package com.xl.designPattern.singleton;

/**
 * @author liangliang
 * @date 2021/1/27 10:25
 * @e-mail 1640432919@qq.com
 */
public class SingletonTest01 {

    public static void main(String[] args) {
        System.out.println(Singleton.getInstance() == Singleton.getInstance());
        System.out.println(Singleton.getInstance().hashCode());
        System.out.println(Singleton.getInstance().hashCode());
    }

}


//饿汉式（静态变量）
class Singleton {

    //构造器私有化（防止 new）
    private Singleton() {

    }

    //类的内部创建对象
    private final static Singleton instance = new Singleton();

    //向外暴露一个静态的公共方法
    public static Singleton getInstance() {
        return instance;
    }

}
