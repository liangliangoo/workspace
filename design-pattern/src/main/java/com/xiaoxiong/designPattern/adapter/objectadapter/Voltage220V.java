package com.xiaoxiong.designPattern.adapter.objectadapter;

/**
 * @author liangliang
 * @date 2021/2/8 10:22
 * @e-mail 1640432919@qq.com
 */
public class Voltage220V {

    public int outPut220V() {
        int src = 220;
        System.out.println("电压为：" + src);
        return src;
    }

}
