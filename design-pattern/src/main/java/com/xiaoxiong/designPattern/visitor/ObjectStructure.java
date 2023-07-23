package com.xiaoxiong.designPattern.visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author liangliang
 * @date 2021/3/6 20:37
 * @e-mail 1640432919@qq.com
 */
public class ObjectStructure {

    private List<Person> personList = new LinkedList<>();

    public void attach(Person person) {
        personList.add(person);
    }

    public void detach(Person person) {
        personList.remove(person);
    }

    public void display(Action action) {
        for (Person person : personList) {
            person.action(action);
        }
    }

}
