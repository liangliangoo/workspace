package com.xiaoxiong.design.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xiongliang
 * @version 1.0
 * @description cglib的拦截器
 * @since 2022/4/7  14:14
 */
public class CglibInterceptor implements MethodInterceptor {

    /**
     * All generated proxied methods call this method instead of the original method.
     * The original method may either be invoked by normal reflection using the Method object,
     * or by using the MethodProxy (faster).
     *
     * @param obj    "this", the enhanced object
     * @param method intercepted Method
     * @param args   argument array; primitive types are wrapped
     * @param proxy  used to invoke super (non-intercepted method); may be called
     *               as many times as needed
     * @return any value compatible with the signature of the proxied method. Method returning void will ignore this value.
     * @throws Throwable any exception may be thrown; if so, super method will not be invoked
     * @see MethodProxy
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // 前置处理
        System.out.println("before:" + method);
        Object invoke = proxy.invokeSuper(obj, args);
        // 后置处理
        System.out.println("after:" + method);
        return invoke;
    }
}
