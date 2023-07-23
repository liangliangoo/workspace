package com.xiaoxiong.designPattern.visitor;

/**
 * @author liangliang
 * @date 2021/3/6 20:27
 * @e-mail 1640432919@qq.com
 */
public class Fail extends Action {

    @Override
    void getManResult(Man man) {
        System.out.println("男性评委给是评价是差评——失败");
    }

    @Override
    void getWomanResult(Woman woman) {
        System.out.println("女性评委给是评价是差评——失败");
    }

}
