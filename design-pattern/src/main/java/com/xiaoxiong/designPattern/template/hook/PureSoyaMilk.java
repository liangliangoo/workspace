package com.xiaoxiong.designPattern.template.hook;

/**
 * @author liangliang
 * @date 2021/3/5 20:46
 * @e-mail 1640432919@qq.com
 */
public class PureSoyaMilk extends SoyaMilk {

    @Override
    void addCondiments() {

    }

    @Override
    boolean wantCondiments() {
        return false;
    }
}
