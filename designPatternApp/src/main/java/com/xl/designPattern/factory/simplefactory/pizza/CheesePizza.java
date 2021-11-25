package com.xl.designPattern.factory.simplefactory.pizza;

/**
 * @author liangliang
 * @date 2021/1/27 15:38
 * @e-mail 1640432919@qq.com
 */
public class CheesePizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("正在准备CheesePizza 原材料");
    }
}
