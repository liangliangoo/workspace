package com.xiaoxiong.algorithm.leetcode.work2023.july;

/**
 * <a href = "https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/">两数之和</a>
 *
 * @Author 六月
 * @Date 2023/7/8 22:06
 * @Version 1.0
 */
public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        return numbers;
    }

    // 暴力穷举 算法
    public int[] force(int[] numbers, int target) {
        if (numbers == null) {
            return null;
        }
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return null;
    }
}
