package com.xl.designPattern.iterator;

import java.util.Iterator;

/**
 * @author liangliang
 * @date 2021/3/7 10:17
 * @e-mail 1640432919@qq.com
 */
public interface College {

    String getName();

    void addDepartment(String name, String description);

    Iterator createIterator();

}
