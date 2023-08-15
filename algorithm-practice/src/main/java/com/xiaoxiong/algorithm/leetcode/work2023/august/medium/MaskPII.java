package com.xiaoxiong.algorithm.leetcode.work2023.august.medium;

/**
 * @author 小熊不会写代码
 * @date 2023/8/15
 */
public class MaskPII {

    String[] country = {"", "+*-", "+**-", "+***-"};

    public String maskPII(String s) {
        int at = s.indexOf("@");
        if (at > 0) {
            s = s.toLowerCase();
            return (s.charAt(0) + "*****" + s.substring(at - 1)).toLowerCase();
        }
        s = s.replaceAll("[^0-9]", "");
        return country[s.length() - 10] + "***-***-" + s.substring(s.length() - 4);
    }

}
