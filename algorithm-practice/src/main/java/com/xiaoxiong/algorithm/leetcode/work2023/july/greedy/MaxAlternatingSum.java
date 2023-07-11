package com.xiaoxiong.algorithm.leetcode.work2023.july.greedy;

/**
 * <a href = "https://leetcode.cn/problems/maximum-alternating-subsequence-sum/"></a>
 * @author xiongliang
 * @version 1.0
 * @description 最大子序列交替和
 * @since 2023/7/11  22:02
 */
public class MaxAlternatingSum {

    /**
     * 贪心:
     * 题目要求偶数下标的减去奇数下标的，而且这些是从数组中取的子序列，也就是说可以取也可以不取
     * 那么就可以看成是股票交易的价格，其中赚的就是差价
     * 我们贪心地收集每一次能够获利的交易就是答案
     * 例如:nums = [6,2,1,2,4,5] 我们可以处理成nums = [0,6,2,1,2,4,5]
     * 在不限制股票交易次数(子序列分割粒度可以到1)的情况下，我们可以将交易粒度最小化，收集每个能够获利的隔天交易
     */
    public long maxAlternating(int[] nums) {
        long result = 0;
        int pre = 0;
        for (int num : nums) {
            if (num > pre) {
                result += num - pre;
            }
            pre = num;
        }
        return result;
    }

}
