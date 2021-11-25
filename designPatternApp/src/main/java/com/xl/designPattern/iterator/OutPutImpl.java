package com.xl.designPattern.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * @author liangliang
 * @date 2021/3/7 12:09
 * @e-mail 1640432919@qq.com
 */
public class OutPutImpl {

    List<College> collegeList;

    public OutPutImpl(List<College> collegeList) {
        this.collegeList = collegeList;
    }

    public void printDepartment(Iterator iterator) {
        while (iterator.hasNext()) {
            Department next = (Department) iterator.next();
            System.out.println(next.getName() + next.getDescription());
        }
    }

    public void printCollege() {
        Iterator<College> iterator = collegeList.iterator();
        while (iterator.hasNext()) {
            College next = iterator.next();
            System.out.println(next.getName());
            printDepartment(next.createIterator());
        }
    }
}
