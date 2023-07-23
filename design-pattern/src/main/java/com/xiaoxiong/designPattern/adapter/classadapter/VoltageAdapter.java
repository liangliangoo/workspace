package com.xiaoxiong.designPattern.adapter.classadapter;

/**
 * @author liangliang
 * @date 2021/2/8 10:24
 * @e-mail 1640432919@qq.com
 */
public class VoltageAdapter extends Voltage220V implements Voltage5V {

    @Override
    public int outPut5V() {
        int src = outPut220V();
        int dst = src / 44;
        return dst;
    }
}
