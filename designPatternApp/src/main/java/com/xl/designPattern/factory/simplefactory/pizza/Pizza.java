package com.xl.designPattern.factory.simplefactory.pizza;

/**
 * @author liangliang
 * @date 2021/1/27 15:32
 * @e-mail 1640432919@qq.com
 */
public abstract class Pizza {

    private String name;

    public abstract  void prepare();

    public void bake() {
        System.out.println(name + " baking");
    }

    public void cut() {
        System.out.println(name + " cutting");
    }

    public void box() {
        System.out.println(name + " boxing");
    }

    public void setName(String name) {
        this.name = name;
    }

}
