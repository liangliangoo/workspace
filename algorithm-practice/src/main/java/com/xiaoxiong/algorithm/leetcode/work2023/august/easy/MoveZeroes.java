package com.xiaoxiong.algorithm.leetcode.work2023.august.easy;

/**
 * <a href="https://leetcode.cn/problems/move-zeroes/?envType=study-plan-v2&envId=leetcode-75">移动零</a>
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

}
