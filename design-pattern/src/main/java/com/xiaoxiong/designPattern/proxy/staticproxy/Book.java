package com.xiaoxiong.designPattern.proxy.staticproxy;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/23  14:02
 */
public class Book implements Goods{
    @Override
    public void sell() {
        System.out.println("sell book");
    }
}
