package com.xiaoxiong.algorithm.leetcode.work2023.august.medium;

/**
 * <a href= "https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/">删除有序数组中的重复项</a>
 * @author 小熊不会写代码
 * @date 2023/8/19
 */
public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        if (nums.length < 3) {
            return nums.length;
        }
        int slow = 2, fast = 2;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow - 2]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    public int removeDuplicatesV1(int[] nums) {
        if (nums.length < 1) {
            return nums.length;
        }
        int slow = 1, fast = 1;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow-1]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

}
