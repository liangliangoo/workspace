package com.xiaoxiong.dubbospi.ioc.service.impl;

import com.xiaoxiong.dubbospi.ioc.service.Car;
import com.xiaoxiong.dubbospi.ioc.service.Food;
import lombok.Setter;
import org.apache.dubbo.common.URL;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/23  18:10
 */
public class Rice implements Food {

    @Setter
    private Car car;

    @Override
    public void introduce() {
        System.out.println("my name is rice");
    }

    @Override
    public void driverCar(URL url) {
        car.getCarBrand(url);
    }
}
