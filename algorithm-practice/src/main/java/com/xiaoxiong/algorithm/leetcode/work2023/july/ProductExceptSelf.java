package com.xiaoxiong.algorithm.leetcode.work2023.july;

/**
 * <a href="https://leetcode.cn/problems/product-of-array-except-self/">除自身以外数组的乘积</a>
 */
public class ProductExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];
        // answer[i] 表示 索引i 左侧所有元素的乘积
        answer[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // r 为右侧 所有元素的乘积
        int r = 1;
        for (int i = length - 1; i >= 0; i--) {
            answer[i] = answer[i] * r;
            r *= nums[i];
        }
        return answer;
    }

}
