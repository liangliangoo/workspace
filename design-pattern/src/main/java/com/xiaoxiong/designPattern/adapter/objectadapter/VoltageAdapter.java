package com.xiaoxiong.designPattern.adapter.objectadapter;

/**
 * @author liangliang
 * @date 2021/2/8 10:24
 * @e-mail 1640432919@qq.com
 */
public class VoltageAdapter implements Voltage5V {

    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V v) {
        this.voltage220V = v;
    }

    @Override
    public int outPut5V() {
        if (voltage220V ==null) {
            System.out.println("电源不匹配");
            return 0;
        } else {
            int src = voltage220V.outPut220V();
            int dst = src / 44;
            return dst;
        }
    }
}
