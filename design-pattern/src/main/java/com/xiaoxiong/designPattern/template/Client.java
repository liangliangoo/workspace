package com.xiaoxiong.designPattern.template;

/**
 * @author liangliang
 * @date 2021/3/5 20:36
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {
        SoyaMilk redBeanSoyaMilk = new RedBeanSoyaMilk();
        redBeanSoyaMilk.make();
    }
}
