package com.xiaoxiong.dubbospi.ioc.service;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/23  18:11
 */
@SPI
public interface Car {

    @Adaptive(value = "carType")
    void getCarBrand(URL url);

}
