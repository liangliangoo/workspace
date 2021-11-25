package com.xl.designPattern.proxy.cglib;

/**
 * @author liangliang
 * @date 2021/2/19 20:25
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {
        TeacherDao teacherDao = new TeacherDao();
        TeacherDao proxyFactory = (TeacherDao) new ProxyFactory(teacherDao).getProxyInstance();
        proxyFactory.teach();

    }
}
