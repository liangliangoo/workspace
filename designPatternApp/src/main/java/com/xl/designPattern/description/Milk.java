package com.xl.designPattern.description;

/**
 * @author liangliang
 * @date 2021/2/13 19:43
 * @e-mail 1640432919@qq.com
 */
public class Milk extends Decorator{

    public Milk(Drink obj) {
        super(obj);
        setDescription("牛奶");
        setPrice(4.0f);
    }
}
