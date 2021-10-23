package com.xl.designPattern.iterator;

import java.util.Iterator;

/**
 * @author liangliang
 * @date 2021/3/7 10:23
 * @e-mail 1640432919@qq.com
 */
public class ComputerCollege implements College {

    Department[] departments;
    int numOfDepartment = 0;

    public ComputerCollege() {
        this.departments = new Department[5];
        addDepartment("计算机1","大数据");
        addDepartment("计算机2","java");
        addDepartment("计算机3","C");
        // addDepartment("计算机4","Web");
        addDepartment("计算机5","Linux");
    }

    @Override
    public String getName() {
        return "计算机学院";
    }

    @Override
    public void addDepartment(String name, String description) {
        departments[numOfDepartment] = new Department(name,description);
        numOfDepartment += 1;
    }

    @Override
    public Iterator createIterator() {
        return new ComputerCollegeIterator(departments);
    }
}
