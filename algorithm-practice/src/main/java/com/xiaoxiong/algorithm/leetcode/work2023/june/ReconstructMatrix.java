package com.xiaoxiong.algorithm.leetcode.work2023.june;


import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/reconstruct-a-2-row-binary-matrix/"></a>
 * @author xiongliang
 * @version 1.0
 * @description 重构 2 行二进制矩阵
 * @since 2023/6/29  20:26
 */
public class ReconstructMatrix {

    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        for (int i = 0; i < colsum.length; i++) {
            int a = 0, b = 0;
            if (colsum[i] == 2) {
                a = b = 1;
                upper--;
                lower--;
            } else if (colsum[i] == 1) {
                if (upper > lower) {
                    upper--;
                    a = 1;
                } else {
                    lower--;
                    b = 1;
                }
            }
            if (upper < 0 || lower < 0) {
                break;
            }
            first.add(a);
            second.add(b);
        }
        List<List<Integer>> ans = new ArrayList<>();
        if (upper == 0 && lower == 0) {
            ans.add(first);
            ans.add(second);
            return ans;
        }
        return new ArrayList<>();
    }

}
