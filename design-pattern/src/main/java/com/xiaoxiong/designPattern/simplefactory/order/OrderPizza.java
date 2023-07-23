package com.xiaoxiong.designPattern.simplefactory.order;

import com.xiaoxiong.designPattern.simplefactory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author liangliang
 * @date 2021/1/27 15:41
 * @e-mail 1640432919@qq.com
 */
public class OrderPizza {

    // public OrderPizza() {
    //     Pizza pizza = null;
    //     String orderType;
    //     do {
    //         orderType = getType();
    //         if (orderType.equals("greek")) {
    //             pizza = new GreekPizza();
    //             pizza.setName("GreekPizza");
    //         } else if (orderType.equals("cheese")) {
    //             pizza = new GreekPizza();
    //             pizza.setName("GreekPizza");
    //         } else if (orderType.equals("prepare")) {
    //             pizza = new PreparePizza();
    //             pizza.setName("PreparePizza");
    //         }
    //         else {
    //             break;
    //         }
    //         pizza.prepare();
    //         pizza.bake();
    //         pizza.cut();
    //         pizza.box();
    //     }while (true);
    // }

    public OrderPizza(SimpleFactory simpleFactory) {
        setSimpleFactory(simpleFactory);
    }

    SimpleFactory simpleFactory;
    Pizza pizza = null;
    public void setSimpleFactory (SimpleFactory simpleFactory) {
        String orderType = "";
        this.simpleFactory = simpleFactory;
        do {
            orderType = getType();
            pizza = this.simpleFactory.createPizza(orderType);
            if (pizza != null) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                System.out.println("订购失败哦");
                break;
            }
        } while (true) ;
    }

    public String getType() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizzaName");
            String line = bufferedReader.readLine();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
