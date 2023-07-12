package com.xiaoxiong.algorithm.leetcode.work2023.july.greedy;

/**
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/"></a>
 * @author xiongliang
 * @version 1.0
 * @description 买股票问题2
 * @since 2023/7/11  22:17
 */
public class MaxProfit2 {

    private int res;

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        this.res = 0;
        dfs(prices, 0, 0, res);
        return this.res;

    }

    /**
     *
     * @param prices 股价数组
     * @param day 第几天
     * @param status 0 表示不持股 ；1表示持股
     * @param profit 当前收益
     */
    private void dfs(int[] prices, int day, int status, int profit) {
        if (day == prices.length) {
            this.res = Math.max(res, profit);
        }
        // 什么都不干
        dfs(prices, day + 1, status, profit);

        if (status == 0) {
            dfs(prices, day + 1, 1, profit - prices[day]);
        } else {
            dfs(prices, day + 1, 0, profit + prices[day]);
        }
    }

    private int dp(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        // 0 持有现金
        // 1 持有股票
        // 状态转移 0 -> 1 -> 0 -> 1
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[dp.length - 1][0];
    }

    private int greedy(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                ans += diff;
            }
        }
        return ans;
    }

}
