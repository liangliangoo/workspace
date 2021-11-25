package com.xl.designPattern.singleton;

/**
 * @author liangliang
 * @date 2021/1/27 11:33
 * @e-mail 1640432919@qq.com
 */
public class SingletonTest07 {
    public static void main(String[] args) {
        Singleton07 instance = Singleton07.INSTANCE;
        Singleton07 instance1 = Singleton07.INSTANCE;
        System.out.println(instance == instance1);
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
        System.out.println(instance);
        System.out.println(instance instanceof Singleton07);
        instance.sayOK();
    }
}

enum Singleton07 {
    INSTANCE; //属性
    public void sayOK() {
        System.out.println("Hello OK");
    }
}
