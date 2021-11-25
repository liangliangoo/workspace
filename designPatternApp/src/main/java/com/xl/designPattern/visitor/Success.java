package com.xl.designPattern.visitor;

/**
 * @author liangliang
 * @date 2021/3/6 20:26
 * @e-mail 1640432919@qq.com
 */
public class Success extends Action {

    @Override
    void getManResult(Man man) {
        System.out.println("男性评委给是评价是满意——成功");
    }

    @Override
    void getWomanResult(Woman woman) {
        System.out.println("女性评委给是评价是满意——成功");
    }
}
