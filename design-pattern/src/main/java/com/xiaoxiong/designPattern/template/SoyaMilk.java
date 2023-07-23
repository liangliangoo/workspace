package com.xiaoxiong.designPattern.template;

/**
 * @author liangliang
 * @date 2021/3/5 20:28
 * @e-mail 1640432919@qq.com
 */
public abstract class SoyaMilk {

    final void make(){
        select();
        addCondiments();
        soak();
        beak();
    }

    void select() {
        System.out.println("第一步：选原料");
    }

    abstract void addCondiments();

    void soak() {
        System.out.println("第三步：浸泡黄豆和配料半个小时");
    }

    void beak() {
        System.out.println("第四步：打豆浆");
    }
}
