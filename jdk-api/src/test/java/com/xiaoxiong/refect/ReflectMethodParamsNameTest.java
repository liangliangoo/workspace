package com.xiaoxiong.refect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 六月
 * @Date 2023/5/31 17:00
 * @Version 1.0
 */
public class ReflectMethodParamsNameTest {

    @Test
    public void test() {
        List<String> paramterNames = getParameterNameJava8(User.class, "setName");
        paramterNames.forEach(System.out::println);
    }

    public static List<String> getParameterNameJava8(Class clazz, String methodName) {
        List<String> paramterList = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                //直接通过method就能拿到所有的参数
                Parameter[] params = method.getParameters();
                for (Parameter parameter : params) {
                    paramterList.add(parameter.getName());
                }
            }
        }
        return paramterList;
    }

    class User {
        private String name;

        public void setName(String name) {
            return;
        }
    }

}
