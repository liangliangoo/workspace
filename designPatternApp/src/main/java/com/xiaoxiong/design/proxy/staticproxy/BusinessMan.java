package com.xiaoxiong.design.proxy.staticproxy;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/23  13:59
 */
public class BusinessMan {

    private Goods goods;

    public BusinessMan(Goods goods) {
        this.goods = goods;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
