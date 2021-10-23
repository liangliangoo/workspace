package com.xl.designPattern.proxy.staticproxy;

/**
 * @author liangliang
 * @date 2021/2/17 20:16
 * @e-mail 1640432919@qq.com
 */
public class TeacherDaoProxy implements ITeacherDao {

    private ITeacherDao target;

    public TeacherDaoProxy(ITeacherDao target) {
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("开始代理");
        target.teach();
        System.out.println("代理结束");
    }
}
