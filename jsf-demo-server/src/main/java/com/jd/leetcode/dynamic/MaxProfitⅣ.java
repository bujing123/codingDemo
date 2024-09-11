package com.jd.leetcode.dynamic;


/**
 *
 * 188.买卖股票的最佳时机IV
 *
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 */
public class MaxProfitⅣ {

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        //股票状态: 偶数表示第 k 次交易持有/买入, 奇数表示第 k 次交易不持有/卖出
        int[][] dp = new int[prices.length][2 * k];
        for (int i = 0; i < 2 * k; i = i+2) {
            dp[0][i] = -prices[0];
        }

        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < 2 * k - 1; j = j + 2) {
                if (j == 0){
                    //第1次买入
                    dp[i][j] = Math.max(dp[i-1][j], -prices[i]);
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]);
                }
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] + prices[i]);
            }
        }
        return dp[prices.length-1][2*k-1];
    }
}
