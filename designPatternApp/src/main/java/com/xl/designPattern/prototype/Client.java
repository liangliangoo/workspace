package com.xl.designPattern.prototype;

/**
 * @author liangliang
 * @date 2021/2/2 10:02
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {
        Sheep sheep = new Sheep("tom", 1, "白色");
        sheep.a = new Sheep("xiaoxiong",1,"棕色");
        Sheep clone = (Sheep) sheep.clone();
        System.out.println(clone.a.hashCode());
        System.out.println(sheep.a.hashCode());
    }


}
