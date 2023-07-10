package com.xiaoxiong.algorithm.leetcode.work2023.july;

import java.util.Arrays;

/**
 * <a hre="https://leetcode.cn/problems/3sum-closest/submissions/"></a>
 * @author xiongliang
 * @version 1.0
 * @description 三数之和
 * @since 2023/7/10  22:36
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // 确保上一次和上一次枚举值不同
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举b\c
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    k = k - 1;
                } else {
                    j = j + 1;
                }
            }

        }
        return best;
    }

}
