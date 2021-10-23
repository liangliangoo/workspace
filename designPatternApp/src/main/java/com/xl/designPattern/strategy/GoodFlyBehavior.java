package com.xl.designPattern.strategy;

/**
 * @author liangliang
 * @date 2021/2/28 15:33
 * @e-mail 1640432919@qq.com
 */
public class GoodFlyBehavior implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("善于飞翔");
    }
}
