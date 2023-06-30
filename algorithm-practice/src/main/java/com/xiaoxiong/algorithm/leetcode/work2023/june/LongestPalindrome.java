package com.xiaoxiong.algorithm.leetcode.work2023.june;

/**
 * 最长回文字串
 * <a href = "https://leetcode.cn/problems/longest-palindromic-substring/"></a>
 * @Author 六月
 * @Date 2023/6/30 21:39
 * @Version 1.0
 */
public class LongestPalindrome {

    public String longestPalindrome(String s) {
        return null;
    }

    public String dynamic(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int leftIdx = 0, rightIdx = 0;
        int maxLen = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int right = 1; right < s.length(); right++) {
            for (int left = 0; left < right; left++) {
                if (s.charAt(left) == s.charAt(right) &&
                        (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                    if (right - left + 1 > maxLen) {
                        maxLen = right - left + 1;
                        leftIdx = left;
                        rightIdx = right;
                    }
                } else {
                    dp[left][right] = false;
                }
            }
        }
        return s.substring(leftIdx, rightIdx + 1);
    }

    public String forceMethod(String s) {
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String test = s.substring(i, j);
                if (isPalindrome(test) && test.length() > ans.length()) {
                    ans = s.substring(i, j);
                }
            }
        }
        return ans;
    }

    public boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

}
