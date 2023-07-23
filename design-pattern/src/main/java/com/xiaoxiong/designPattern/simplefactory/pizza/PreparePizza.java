package com.xiaoxiong.designPattern.simplefactory.pizza;

/**
 * @author liangliang
 * @date 2021/1/27 15:54
 * @e-mail 1640432919@qq.com
 */
public class PreparePizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("正在为PreparePizza准备原材料");
    }
}
