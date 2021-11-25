package com.xl.designPattern.template;

/**
 * @author liangliang
 * @date 2021/3/5 20:33
 * @e-mail 1640432919@qq.com
 */
public class RedBeanSoyaMilk extends SoyaMilk {

    @Override
    void addCondiments() {
        System.out.println("第二步：加入红豆");
    }
}
