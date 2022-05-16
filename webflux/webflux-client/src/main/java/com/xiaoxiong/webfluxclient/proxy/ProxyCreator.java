package com.xiaoxiong.webfluxclient.proxy;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/11  19:32
 */
public interface ProxyCreator {

    Object createProxy(Class<?> type);

}
