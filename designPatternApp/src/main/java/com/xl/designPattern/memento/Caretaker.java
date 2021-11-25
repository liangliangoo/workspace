package com.xl.designPattern.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangliang
 * @date 2021/3/7 14:42
 * @e-mail 1640432919@qq.com
 */
public class Caretaker {

    private List<Memento> mementos = new ArrayList<>();

    public void add(Memento memento) {
        mementos.add(memento);
    }

    public Memento getMemento(int index) {
        return mementos.get(index);
    }

}
