package com.xiaoxiong.dubbospi.ioc.service;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/23  18:08
 */
@SPI
public interface Food {

    void introduce();

    void driverCar(URL url);

}
