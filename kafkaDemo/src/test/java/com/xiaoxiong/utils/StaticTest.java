package com.xiaoxiong.utils;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/12/2  10:19
 */
public class StaticTest {

    static {
        System.out.println("static");
    }

    public StaticTest() {
        System.out.println("construct");
    }

    public static void main(String[] args) {
        StaticTest staticTest = new StaticTest();
    }

}
