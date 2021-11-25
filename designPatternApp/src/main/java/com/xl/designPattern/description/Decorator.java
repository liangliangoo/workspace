package com.xl.designPattern.description;

/**
 * @author liangliang
 * @date 2021/2/13 19:36
 * @e-mail 1640432919@qq.com
 */
public class Decorator extends Drink{

    private Drink obj;

    public Decorator(Drink obj) {
        this.obj = obj;
    }

    @Override
    public float cost() {
        return super.getPrice() + obj.getPrice();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "   " + super.getPrice() + "&&" + obj.getDescription();
    }
}
