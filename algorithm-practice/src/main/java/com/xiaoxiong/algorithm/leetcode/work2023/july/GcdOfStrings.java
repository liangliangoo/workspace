package com.xiaoxiong.algorithm.leetcode.work2023.july;

/**
 * <a href="https://leetcode.cn/problems/greatest-common-divisor-of-strings/?envType=study-plan-v2&envId=leetcode-75">
 *     字符串的最大公因子</a>
 */
public class GcdOfStrings {

    public String gcdOfStrings(String str1, String str2) {
        return "";
    }

    private String mathAlg(String str1, String str2) {
        if (!str1.concat(str2).equals(str2.concat(str1))) {
            return "";
        }
        return str1.substring(0, subLen(str1.length(), str2.length()));
    }

    private int subLen(int a, int b) {
        int remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }

}
