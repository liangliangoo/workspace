package com.xl.designPattern.mybuilder;

/**
 * @author liangliang
 * @date 2021/2/2 19:49
 * @e-mail 1640432919@qq.com
 */
public abstract class AbstractHouse {

    public abstract void buildBasic();

    public abstract void buildWalls();

    public abstract void roofed();

    public void build() {
        buildBasic();
        buildWalls();
        roofed();
    }

}
