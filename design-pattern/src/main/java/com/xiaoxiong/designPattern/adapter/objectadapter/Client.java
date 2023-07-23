package com.xiaoxiong.designPattern.adapter.objectadapter;



/**
 * @author liangliang
 * @date 2021/2/8 10:29
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {

        Phone phone = new Phone();
        phone.charging(new VoltageAdapter(new Voltage220V()));

    }

}
