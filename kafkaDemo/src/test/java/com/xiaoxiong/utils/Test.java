package com.xiaoxiong.utils;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/30  16:18
 */
public class Test {

    public Test() {
        init(this);
    }

    public void init(Test test) {
        System.out.println(test.getClass().getName());
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.init(test);
    }
}
