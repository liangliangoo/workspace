package com.xiaoxiong.springdemo.di.case1;

import org.springframework.stereotype.Service;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/6/4  23:03
 */
@Service
public class ConstructorService1 {


    private ConstructorService2 constructorService2;

    /**
     * 与无参构造函数不同之处在于，依赖属性的getBean调用提前了，无参构造函数是在类实例化完成，
     * 并且放入到了三级缓存中之后才调用的，而现在不是了，也就是说现在缓存中并没有TestA。
     * <a hreef="https://blog.csdn.net/CSDN_WYL2016/article/details/108146174">参考博客</a>
     * @param constructorService2
     */

    public ConstructorService1(ConstructorService2 constructorService2) {
        this.constructorService2 = constructorService2;
    }

}
