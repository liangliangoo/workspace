package com.xl.designPattern.proxy.staticproxy;

/**
 * @author liangliang
 * @date 2021/2/17 20:14
 * @e-mail 1640432919@qq.com
 */
public class TeacherDao implements ITeacherDao {

    @Override
    public void teach() {
        System.out.println("同学们，我要开始上课啦！");
    }
}
