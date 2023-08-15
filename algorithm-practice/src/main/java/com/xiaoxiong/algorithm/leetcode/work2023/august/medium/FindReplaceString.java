package com.xiaoxiong.algorithm.leetcode.work2023.august.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 *     放弃
 * </p>
 * <a href="https://leetcode.cn/problems/find-and-replace-in-string/description/">833. 字符串中的查找与替换</a>
 * @author 小熊不会写代码
 * @date 2023/8/15
 */
public class FindReplaceString {

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length(), m = indices.length;
        List<Integer> ops = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            ops.add(i);
        }
        ops.sort(Comparator.comparingInt(i -> indices[i]));
        StringBuilder ret = new StringBuilder();
        int pr = 0;
        for (int i = 0; i < n; ) {
            while (pr < m && indices[ops.get(pr)] < i) {
                pr++;
            }
            boolean success = false;
            while (pr < m && indices[ops.get(pr)] == i) {
                if (s.substring(i, Math.min(i + sources[ops.get(pr)].length(), n)).equals(sources[pr])) {
                    success = true;
                    break;
                }
            }
        }
        return ret.toString();
    }

}
