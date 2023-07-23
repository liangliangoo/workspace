package com.xiaoxiong.designPattern.simplefactory.pizzastore;

import com.xiaoxiong.designPattern.simplefactory.order.OrderPizza;
import com.xiaoxiong.designPattern.simplefactory.order.SimpleFactory;

/**
 * @author liangliang
 * @date 2021/1/27 15:49
 * @e-mail 1640432919@qq.com
 */
public class PizzaStore {

    public static void main(String[] args) {
        // new OrderPizza();
        new OrderPizza(new SimpleFactory());
    }

}
