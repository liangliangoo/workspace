package com.xiaoxiong.algorithm.leetcode.work2023.august.easy;

/**
 * <a href="https://leetcode.cn/problems/is-subsequence/?envType=study-plan-v2&envId=leetcode-75">判断子序列</a>
 */
public class IsSubsequence {

    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            }
            j++;
        }
        return i == s.length() - 1;
    }

}
