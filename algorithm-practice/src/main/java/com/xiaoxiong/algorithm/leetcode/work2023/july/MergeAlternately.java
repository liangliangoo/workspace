package com.xiaoxiong.algorithm.leetcode.work2023.july;

/**
 * <a href="https://leetcode.cn/problems/merge-strings-alternately/?envType=study-plan-v2&envId=leetcode-75"></a>
 * @Author 六月
 * @Date 2023/7/24 13:55
 * @Version 1.0
 */
public class MergeAlternately {

    public String mergeAlternately(String word1, String word2) {
        if (word2 == null || word1 == null) {
            return word1 + word2;
        }
        StringBuilder ret = new StringBuilder();
        int first = 0, second = 0;
        while (first < word1.length() || second < word2.length()) {
            if (first < word1.length()) {
                ret.append(word1.charAt(first));
            }
            if (second < word2.length()) {
                ret.append(word2.charAt(second));
            }
            first++;
            second++;
        }
        return ret.toString();
    }

}
