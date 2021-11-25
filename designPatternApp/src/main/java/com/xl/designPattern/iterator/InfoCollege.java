package com.xl.designPattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author liangliang
 * @date 2021/3/7 12:04
 * @e-mail 1640432919@qq.com
 */
public class InfoCollege implements College {

    List<Department> departmentList;

    public InfoCollege() {
        this.departmentList = new ArrayList<>();
        addDepartment("信息安全", "信息安全");
        addDepartment("网络安全", "网络安全");
        addDepartment("数据库安全", "数据库安全");
        addDepartment("防火墙安全", "防火墙安全");
    }

    @Override
    public String getName() {
        return "信息工程学院";
    }

    @Override
    public void addDepartment(String name, String description) {
        departmentList.add(new Department(name, description));

    }

    @Override
    public Iterator createIterator() {
        return new InfoIterator(departmentList);
    }
}
