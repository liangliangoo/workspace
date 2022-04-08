package com.xiaoxiong.design.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.jupiter.api.Test;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/4/7  14:38
 */
public class ManTest {

    @Test
    void test_invoke() {

        // System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"F:\\JavaProject\\appdemo\\designPatternApp\\target\\test-classes");
        // 解决循环proxy.invoke(obj,args)的时候 导致循环调用
        // 这里的写法就有点和jdk代理类似了
        Man target = new Man();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Man.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            System.out.println("before:" + method);

            // invoke方法调用的对象没有增强过，invokeSuper方法调用的对象已经是增强了的--》
            // method.invoke()方法会产生循环调用，实际不断的 走 被代理对象的的方法与 代理对象的intercept 方法， 造成了循环调用， 并不是没有被增强。
            Object invoke = proxy.invoke(target, args);
            System.out.println(invoke);
            System.out.println("after:" + method);
            return invoke;
        });
        Man man = (Man) enhancer.create();
        man.test();
    }

}
