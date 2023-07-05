package com.xiaoxiong.algorithm.leetcode.work2023.july.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/as-far-from-land-as-possible/"></a>
 * @author xiongliang
 * @version 1.0
 * @description 地图分析
 * @since 2023/7/5  22:45
 */
public class MaxDistance {

    public int maxDistance(int[][] grid) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<int[]> queue = new ArrayDeque<>();
        int m = grid.length, n = grid[0].length;
        // 先把所有的陆地入队
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        // 从陆地开始遍历
        boolean hasOcean = false;
        int[] point = null;
        while (!queue.isEmpty()) {
            point = queue.poll();
            int x = point[0], y = point[1];
            // 获取四周的海洋将其改为陆地 持续向前推进
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] != 0) {
                    continue;
                }
                grid[newX][newY] = grid[x][y] + 1;// 标记
                hasOcean = true;
                queue.add(new int[]{newX, newY});
            }
        }
        if (point == null || !hasOcean) {
            return -1;
        }

        return grid[point[0]][point[1]] - 1;
    }
}
