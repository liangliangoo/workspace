package com.xl.designPattern.proxy.jdkproxy;

public class TeacherDao implements ITeacherDao {

    @Override
    public void teach() {
        System.out.println("同学们，我要开始上课啦！");
    }

}