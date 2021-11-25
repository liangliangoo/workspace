package com.xl.designPattern.responsibilityChain;

/**
 * @author liangliang
 * @date 2021/2/21 16:25
 * @e-mail 1640432919@qq.com
 */
public class PurchaseRequest {
    private int type;
    private float price;
    private int id;
    public PurchaseRequest(int type,  float price, int id) {
        this.type = type;
        this.price = price;
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
}
