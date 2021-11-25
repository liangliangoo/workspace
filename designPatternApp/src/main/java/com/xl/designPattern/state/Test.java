package com.xl.designPattern.state;

/**
 * @author liangliang
 * @date 2021/3/7 15:59
 * @e-mail 1640432919@qq.com
 */
public class Test {
    public static void main(String[] args) {
        String s = "xiao";
        String s1 = "xiong";
        String s2 = s + s1;
        System.out.println(s2 == "xiaoxiong");
    }
}
