package com.xiaoxiong.algorithm.leetcode.work2023.july;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * <a hred="https://leetcode.cn/problems/reverse-words-in-a-string/?envType=study-plan-v2&envId=leetcode-75">反转字符串中的单词</a>
 * @Author 六月
 * @Date 2023/7/24 14:00
 * @Version 1.0
 */
public class ReverseWords {

    public String reverseWords(String s) {
        s.trim();
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

}
