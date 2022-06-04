package com.xiaoxiong.springdemo.di.case1;

import org.springframework.stereotype.Service;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/6/4  23:04
 */
@Service
public class ConstructorService2 {

    private ConstructorService1 constructService1;

    public ConstructorService2(ConstructorService1 constructService1) {
        this.constructService1 = constructService1;
    }
}
