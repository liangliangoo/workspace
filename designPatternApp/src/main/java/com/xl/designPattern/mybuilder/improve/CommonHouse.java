package com.xl.designPattern.mybuilder.improve;

/**
 * @author liangliang
 * @date 2021/2/2 20:27
 * @e-mail 1640432919@qq.com
 */
public class CommonHouse extends HouseBuilder {

    @Override
    public void buildBasic() {
        System.out.println("给普通房子打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("给普通房子垒墙");
    }

    @Override
    public void roofed() {
        System.out.println("给普通房子盖房顶");
    }
}