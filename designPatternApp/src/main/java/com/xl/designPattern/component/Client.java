package com.xl.designPattern.component;

/**
 * @author liangliang
 * @date 2021/2/14 20:44
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {
        OrganizationComponent university = new University("浙江海洋大学", "浙江海洋大学位于浙江舟山");
        OrganizationComponent college1 = new College("信息工程学院", "信息工程学院");
        OrganizationComponent college2 = new College("计算机学院", "计算机专业");
        college1.add(new Department("电子工程","电子工程就业方便"));
        college1.add(new Department("电信专业","电信专业不好学"));
        college2.add(new Department("软件工程","软件工程考研率高"));
        college2.add(new Department("计算机科学与技术","计算机科学与技术不好学"));
        university.add(college1);
        university.add(college2);
        university.show();
    }

}