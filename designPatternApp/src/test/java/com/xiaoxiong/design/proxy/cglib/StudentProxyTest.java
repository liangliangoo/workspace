package com.xiaoxiong.design.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import org.junit.jupiter.api.Test;

/**
 * @author xiongliang
 * @version 1.0
 * @description student代理测试
 * @since 2022/4/7  14:20
 */
public class StudentProxyTest {

    @Test
    void test() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"F:\\debug");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Student.class);
        enhancer.setCallback(new CglibInterceptor());
        Student student = (Student) enhancer.create();
        student.sayHello();
        // final 修饰的方法
        student.student();
    }

}
