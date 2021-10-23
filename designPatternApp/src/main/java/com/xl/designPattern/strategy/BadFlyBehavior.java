package com.xl.designPattern.strategy;

/**
 * @author liangliang
 * @date 2021/2/28 15:34
 * @e-mail 1640432919@qq.com
 */
public class BadFlyBehavior implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("不善于飞翔");
    }

}
