package com.xl.designPattern.mybuilder.improve;

/**
 * @author liangliang
 * @date 2021/2/2 20:30
 * @e-mail 1640432919@qq.com
 */
public class HouseDirector {

    HouseBuilder houseBuilder = null;

    //构造器方法
    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    //set方法
    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    // 如何处理建造房子的流程
    public House constructHouse() {
        houseBuilder.buildBasic();
        houseBuilder.buildWalls();
        houseBuilder.roofed();
        return houseBuilder.buildHouse();
    }

}
