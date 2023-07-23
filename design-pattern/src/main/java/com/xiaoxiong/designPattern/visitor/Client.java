package com.xiaoxiong.designPattern.visitor;

/**
 * @author liangliang
 * @date 2021/3/6 20:40
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.attach(new Man());
        objectStructure.attach(new Woman());
        objectStructure.display(new Success());
    }
}
