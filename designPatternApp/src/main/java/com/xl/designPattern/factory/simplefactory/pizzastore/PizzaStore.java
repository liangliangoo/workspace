package com.xl.designPattern.factory.simplefactory.pizzastore;

import com.xl.designPattern.factory.simplefactory.order.OrderPizza;
import com.xl.designPattern.factory.simplefactory.order.SimpleFactory;

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
