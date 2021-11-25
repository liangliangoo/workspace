package com.xl.designPattern.memento;

/**
 * @author liangliang
 * @date 2021/3/7 14:44
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        originator.setState("状态1");
        caretaker.add(originator.saveStateMemento());
        originator.setState("状态2");
        System.out.println(originator.getState());
        originator.getStateFromMemento(caretaker.getMemento(0));
        System.out.println(originator.getState());
    }
}
