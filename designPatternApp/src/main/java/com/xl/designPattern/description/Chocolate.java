package com.xl.designPattern.description;

/**
 * @author liangliang
 * @date 2021/2/13 19:41
 * @e-mail 1640432919@qq.com
 */
public class Chocolate extends Decorator{

    public Chocolate(Drink obj) {
        super(obj);
        setDescription("巧克力");
        setPrice(2.0f);
    }
}
