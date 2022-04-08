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
        // ���ѭ��proxy.invoke(obj,args)��ʱ�� ����ѭ������
        // �����д�����е��jdk����������
        Man target = new Man();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Man.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            System.out.println("before:" + method);

            // invoke�������õĶ���û����ǿ����invokeSuper�������õĶ����Ѿ�����ǿ�˵�--��
            // method.invoke()���������ѭ�����ã�ʵ�ʲ��ϵ� �� ���������ĵķ����� ��������intercept ������ �����ѭ�����ã� ������û�б���ǿ��
            Object invoke = proxy.invoke(target, args);
            System.out.println(invoke);
            System.out.println("after:" + method);
            return invoke;
        });
        Man man = (Man) enhancer.create();
        man.test();
    }

}
