package com.xl.designPattern.mybuilder.improve;

/**
 * @author liangliang
 * @date 2021/2/2 20:29
 * @e-mail 1640432919@qq.com
 */
public class HightBuilding extends HouseBuilder{

    @Override
    public void buildBasic() {
        System.out.println("给高楼打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("给高楼砌墙");
    }

    @Override
    public void roofed() {
        System.out.println("给高楼盖屋顶");
    }


}
