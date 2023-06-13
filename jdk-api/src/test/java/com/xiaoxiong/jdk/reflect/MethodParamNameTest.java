package com.xiaoxiong.jdk.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 反射获取方法参数
 * @Author 六月
 * @Date 2023/6/12 22:33
 * @Version 1.0
 */
public class MethodParamNameTest {

    @Test
    public void test_methodParam() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.xiaoxiong.jdk.reflect.ReflectUser");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getName());
            }
        }

    }

}
