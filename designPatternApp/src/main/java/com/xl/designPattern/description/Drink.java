package com.xl.designPattern.description;

/**
 * @author liangliang
 * @date 2021/2/13 19:22
 * @e-mail 1640432919@qq.com
 */
public abstract class Drink {

    public String description;

    private float price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    //计算费用的抽象方法
    //子类来实现
    public abstract float cost();
}
