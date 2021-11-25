package com.xl.designPattern.adapter.objectadapter;

import com.xl.designPattern.adapter.classadapter.Voltage5V;

/**
 * @author liangliang
 * @date 2021/2/8 10:27
 * @e-mail 1640432919@qq.com
 */
public class Phone {

    public void charging(Voltage5V i){
        if (i.outPut5V() == 5) {
            System.out.println("电压适合，可以充电");
        } else {
            System.out.println("电压不行，不能充电");
        }
    }

}
