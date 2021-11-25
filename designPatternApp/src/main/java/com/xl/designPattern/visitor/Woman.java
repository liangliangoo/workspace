package com.xl.designPattern.visitor;

/**
 * @author liangliang
 * @date 2021/3/6 20:25
 * @e-mail 1640432919@qq.com
 */
public class Woman extends Person {

    @Override
    void action(Action action) {
        action.getWomanResult(this);
    }
}
