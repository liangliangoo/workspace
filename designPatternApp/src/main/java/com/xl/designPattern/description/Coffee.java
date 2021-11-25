package com.xl.designPattern.description;

/**
 * @author liangliang
 * @date 2021/2/13 19:26
 * @e-mail 1640432919@qq.com
 */
public class Coffee extends Drink {

    @Override
    public float cost() {
        return super.getPrice();
    }
}
