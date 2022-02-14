package com.xiaoxiong.mockdata.enity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/2/14 12:00
 */

@Component
public class A {

    @Autowired
    private B b; // 完全mock
    @Autowired
    private C c; // 需要执行方法

    @Autowired D d; // 需要执行真实方法

    public void func(){
        System.out.println("a");
    }
}
