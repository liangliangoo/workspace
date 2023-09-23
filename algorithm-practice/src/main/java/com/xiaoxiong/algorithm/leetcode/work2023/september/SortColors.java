package com.xiaoxiong.algorithm.leetcode.work2023.september;

/**
 *
 * <a href="https://leetcode.cn/problems/sort-colors/?envType=study-plan-v2&envId=top-100-liked">颜色分类</a>
 *
 * @author 小熊不会写代码
 * @date 2023/9/23
 */
public class SortColors {

    public void sortColors(int[] nums) {
        if (nums == null || nums.length < 1) {
            return;
        }
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                int tmp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = tmp;
                ptr++;
            }
        }
        for (int i = ptr; i < n; i++) {
            if (nums[i] == 1) {
                int tmp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = tmp;
                ptr++;
            }
        }
    }

}
