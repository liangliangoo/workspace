package com.xl.designPattern.proxy.jdkproxy;

/**
 * @author liangliang
 * @date 2021/2/19 19:38
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {
        ITeacherDao target = new TeacherDao();
        ITeacherDao instance = (ITeacherDao) new ProxyFactory(target).getProxyInstance();
        System.out.println(instance);
        instance.teach();
    }

}
