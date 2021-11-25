package com.xl.designPattern.template.hook;

/**
 * @author liangliang
 * @date 2021/3/5 20:47
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {
        SoyaMilk pureMilk = new PureSoyaMilk();
        pureMilk.make();
    }

}
