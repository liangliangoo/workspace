package com.xiaoxiong.designPattern.visitor;

/**
 * @author liangliang
 * @date 2021/3/6 20:24
 * @e-mail 1640432919@qq.com
 */
public class Man extends Person {

    @Override
    void action(Action action) {
        action.getManResult(this);
    }
}
