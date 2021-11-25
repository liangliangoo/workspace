package com.xl.designPattern.memento;

/**
 * @author liangliang
 * @date 2021/3/7 14:37
 * @e-mail 1640432919@qq.com
 */
public class Originator {

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento saveStateMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento) {
        state = memento.getState();

    }
}
