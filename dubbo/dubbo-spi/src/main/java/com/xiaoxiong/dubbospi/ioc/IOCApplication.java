package com.xiaoxiong.dubbospi.ioc;

import com.xiaoxiong.dubbospi.ioc.service.Food;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.HashMap;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/23  18:15
 */
public class IOCApplication {

    public static void main(String[] args) {
        ExtensionLoader<Food> extensionLoader = ExtensionLoader.getExtensionLoader(Food.class);
        Food rice = extensionLoader.getExtension("rice");
        HashMap<String, String> map = new HashMap<>();
        map.put("carType", "porsche");
        URL url = new URL("","",0,map);
        rice.driverCar(url);
    }

}
