package com.xiaoxiong.algorithm.leetcode.work2023.june;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/"></a>
 *
 * @author xiongliang
 * @version 1.0
 * @description 无重复最长子串
 * @since 2023/6/28  22:27
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (Objects.isNull(s) || s.length() < 1) {
            return 0;
        }
        return forceMethod(s);
    }

    private int forceMethod(String s) {
        int length = s.length();
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int start = 0, end = 0; end < length; end++) {
            char element = s.charAt(end);
            if (map.containsKey(element)) {
                start = Math.max(map.get(element) + 1, start); //map.get()的地方进行+1操作
            }
            max = Math.max(max, end - start + 1);
            map.put(element, end);
        }
        return max;
    }

    private int slidingWindow(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

}
