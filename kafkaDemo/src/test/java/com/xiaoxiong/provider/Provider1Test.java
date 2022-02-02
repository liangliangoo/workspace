package com.xiaoxiong.provider;

import org.junit.Test;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/30  19:09
 */
public class Provider1Test {

    @Test
    public void provide() {
        Provider1 provider = new Provider1();
        provider.provide("test");
    }

}
