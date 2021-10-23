package com.xl.designPattern.flywight;

import java.util.HashMap;

/**
 * @author liangliang
 * @date 2021/2/17 16:55
 * @e-mail 1640432919@qq.com
 */
public class WebSiteFactory {

    private HashMap<String,ConcreteWebSite> pools = new HashMap<>();

    //根据网站类型，返回一个网站，如果没有则创建
    public WebSite getWebSiteCategory(String type) {
        if ( !pools.containsKey(type)) {
            pools.put(type, new ConcreteWebSite(type));
        }
        return pools.get(type);
    }

    public int getWebSiteCount() {
        return pools.size();
    }

}
