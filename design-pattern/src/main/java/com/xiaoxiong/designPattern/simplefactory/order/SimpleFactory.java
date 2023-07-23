package com.xiaoxiong.designPattern.simplefactory.order;

import com.xiaoxiong.designPattern.simplefactory.pizza.GreekPizza;
import com.xiaoxiong.designPattern.simplefactory.pizza.Pizza;
import com.xiaoxiong.designPattern.simplefactory.pizza.PreparePizza;

/**
 * @author liangliang
 * @date 2021/1/27 15:58
 * @e-mail 1640432919@qq.com
 */
public class SimpleFactory {

    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("greek")) {
            pizza = new GreekPizza();
            pizza.setName("GreekPizza");
        } else if (orderType.equals("cheese")) {
            pizza = new GreekPizza();
            pizza.setName("GreekPizza");
        } else if (orderType.equals("prepare")) {
            pizza = new PreparePizza();
            pizza.setName("PreparePizza");
        }
        return pizza;
    }

}
