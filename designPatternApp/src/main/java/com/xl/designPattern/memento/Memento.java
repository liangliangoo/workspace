package com.xl.designPattern.memento;

/**
 * @author liangliang
 * @date 2021/3/7 14:38
 * @e-mail 1640432919@qq.com
 */
public class Memento {

    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}
