package com.xiaoxiong.designPattern.simplefactory.pizza;

/**
 * @author liangliang
 * @date 2021/1/27 15:39
 * @e-mail 1640432919@qq.com
 */
public class GreekPizza extends Pizza{

    @Override
    public void prepare() {
        System.out.println("正在给GreekPizza 准备原材料");
    }
}
