package com.xiaoxiong.algorithm.leetcode.work2023.july;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/word-break/">单词拆分</a>
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2023/7/18  22:44
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        /**
         * 动态规划算法
         * 状态转移方程
         * dp[i]表示 字符串s前i 个字符组成的字符串s[0.i-1]是否能够被空格拆分成若干个字典中出现的单词
         * dp[i]=dp[j] && check(s[j..i−1])
         */
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}
