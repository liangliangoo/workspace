package com.xiaoxiong.webfluxclient.interfaces;

import com.xiaoxiong.webfluxclient.domain.MethodInfo;
import com.xiaoxiong.webfluxclient.domain.ServerInfo;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/11  19:54
 */
public interface RestHandler {

    /**
     * 调用rest请求，返回接口
     * @param methodInfo
     * @return
     */
    Object invokeRest(MethodInfo methodInfo);

    void init(ServerInfo serverInfo);
}
