package com.xl.designPattern.mybuilder.improve;

/**
 * @author liangliang
 * @date 2021/2/2 20:22
 * @e-mail 1640432919@qq.com
 */
public abstract class HouseBuilder {

    protected House house = new House();

    public abstract void buildBasic();

    public abstract void buildWalls();

    public abstract void roofed();

    public void build() {
        buildBasic();
        buildWalls();
        roofed();
    }

    public House buildHouse() {
        return house;
    }

}
