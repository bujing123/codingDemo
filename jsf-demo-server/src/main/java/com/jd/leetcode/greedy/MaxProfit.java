package com.jd.leetcode.greedy;


/**
 * 122. 买卖股票的最佳时机 II
 * <p>
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * <p>
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * <p>
 * 返回 你能获得的 最大 利润 。
 * <p>
 * 贪心思路：
 * 每次小钱我都赚！这样子就是最大利益，只要前后有差价，我就赚！
 * 1 2 3 4 5  从1到2，赚1；从2到3，赚1；
 */
public class MaxProfit {

    public int maxProfit(int[] prices) {
        if (prices.length < 1) return 0;

        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;

    }

    /**
     * 动态规划
     *
     *      dp[i][0] 代表第i天持有股票的最大现金       =   max(dp[i-1][0]， dp[i-1][1] - prices[i])
     *      dp[i][1] 代表第i天不持有股票的最大现金     =    max(prices[i]-dp[i-1][0], dp[i-1][1])
     */

    public int maxProfit2(int[] prices){
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], prices[i] + dp[i - 1][0]);
        }
        return dp[prices.length-1][1];
    }

}
