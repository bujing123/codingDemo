package com.jd.leetcode.dynamic;


/**
 * 121. 买卖股票的最佳时机
 * <p>
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */

public class MaxProfit {

    /**
     * 从左往右依次取最小值，然后每天都已当前价减去当前的最低值，就是对于当前来说可以赚到的最大价值
     *
     * 贪心思路：每天都去找对于当前来说最低的价值是多少，减去就是对于当前来书最高的价值
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        //贪心算法
        int max = 0;
        int buy = prices[0];
        //从左往右循环，取最小的值作为买入点位
        //每次取当前值减去最小值，获取最大的差值作为利润
        for (int price : prices) {
            buy = Math.min(buy, price);
            max = Math.max(max, price - buy);
        }
        return max;
    }

    /**
     * 动态规划
     *
     * @param prices
     * @return //           dp[i][0]代表第i天持有股票的最大收益
     * dp[i][1]代表第i天不持有股票的最大收益
     * <p>
     *      dp[i][0] = max(dp[i-1][0], -prices[i])  今天持有股票的收益，是前一天持有，和今天持有取最大值，就是看哪天买更合适
     *      dp[i][1] = max(dp[i-1][0] + prices[i], dp[i-1][1])  今天不持有，说明卖了；比较前一天就卖了的，和今天卖昨天继续持有的收益哪个更高
     */
    public int maxProfit2(int[] prices) {
        int[][] dp = new int[prices.length][2];

        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i-1][0] + prices[i], dp[i-1][1]);
        }

        return dp[prices.length-1][1];

    }


}
