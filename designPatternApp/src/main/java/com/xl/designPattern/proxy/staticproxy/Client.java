package com.xl.designPattern.proxy.staticproxy;

/**
 * @author liangliang
 * @date 2021/2/17 20:24
 * @e-mail 1640432919@qq.com
 */
public class Client {
    public static void main(String[] args) {
        TeacherDaoProxy proxy = new TeacherDaoProxy(new TeacherDao());
        proxy.teach();
    }
}
