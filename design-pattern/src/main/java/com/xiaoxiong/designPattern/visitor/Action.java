package com.xiaoxiong.designPattern.visitor;

/**
 * @author liangliang
 * @date 2021/3/6 20:22
 * @e-mail 1640432919@qq.com
 */
public abstract class Action {

    abstract void getManResult(Man man);

    abstract void getWomanResult(Woman woman);
}
