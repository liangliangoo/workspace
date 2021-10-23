package com.xl.designPattern.mybuilder.improve;

/**
 * @author liangliang
 * @date 2021/2/2 20:38
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {
        CommonHouse commonHouse = new CommonHouse();
        HouseDirector houseDirector = new HouseDirector(commonHouse);
        House house = houseDirector.constructHouse();
        houseDirector.setHouseBuilder(new HightBuilding());
        houseDirector.constructHouse();
    }
}
