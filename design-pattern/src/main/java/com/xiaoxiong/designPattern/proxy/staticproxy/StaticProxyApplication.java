package com.xiaoxiong.designPattern.proxy.staticproxy;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/23  14:03
 */
public class StaticProxyApplication {

    public static void main(String[] args) {
        BusinessMan businessMan = new BusinessMan(new Book());
        businessMan.getGoods().sell();
    }

}
