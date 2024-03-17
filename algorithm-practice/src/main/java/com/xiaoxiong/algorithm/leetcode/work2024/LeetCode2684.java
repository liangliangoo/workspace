package com.xiaoxiong.algorithm.leetcode.work2024;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/maximum-number-of-moves-in-a-grid/description/?envType=daily-question&envId=2024-03-16
 *
 * @author 小熊不会写代码
 * @date 2024/3/16
 */
public class LeetCode2684 {

    private int ans;

    public int maxMoves(int[][] grid) {
        // 每一行开始
        for (int i = 0; i < grid.length; i++) {
            dfs(i, 0, grid);
        }
        return 0;
    }

    public int bsf(int[][] grid) {
        // 行
        int m = grid.length;
        // 列
        int n = grid[0].length;
        Set<Integer> q = new HashSet<>();
        for (int i = 0; i < m; i++) {
            q.add(i);
        }
        for (int j = 1; j < n; j++) {
            Set<Integer> q2 = new HashSet<>();
            for (int i : q) {
                for (int i2 = i - 1; i2 <= i + 1; i2++) {
                    if (0 <= i2 && i2 < m && grid[i][j - 1] < grid[i2][j]) {
                        q2.add(i2);
                    }
                }
            }
            q = q2;
            if (q.isEmpty()) {
                return j - 1;
            }
        }
        return n - 1;
    }

    public void dfs(int i, int j, int[][] grid) {
        ans = Math.max(ans, j);
        // 此时已经是最优解了
        if (ans == grid[0].length) {
            return;
        }
        // 向右上/右/右下 走一步
        for (int k = Math.max(i - 1, 0); k < Math.min(i + 2, grid.length); k++) {
            if (j + 1 < grid[0].length && grid[k][j + 1] > grid[i][j]) {
                dfs(k, j + 1, grid);
            }
        }
        grid[i][j] = 0;
    }

}
