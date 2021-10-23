package com.xiaoxiong.dubbospi.ioc.service.impl;

import com.xiaoxiong.dubbospi.ioc.service.Car;
import org.apache.dubbo.common.URL;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/23  18:12
 */
public class Porsche implements Car {

    @Override
    public void getCarBrand(URL url) {
        System.out.println("my brand is porsche");
    }

}
