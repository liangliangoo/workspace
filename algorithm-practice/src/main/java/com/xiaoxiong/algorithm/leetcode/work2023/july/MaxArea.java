package com.xiaoxiong.algorithm.leetcode.work2023.july;

/**
 * https://leetcode.cn/problems/container-with-most-water/
 * @author xiongliang
 * @version 1.0
 * @description 水池最大面积
 * @since 2023/7/1  21:37
 */
public class MaxArea {

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int l = 0, r = height.length;
        int area = 0;
        while (l < r) {
            int tmp = (r - l) * Math.max(height[r], height[l]);
            area = Math.max(area, tmp);
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return area;
    }

}
