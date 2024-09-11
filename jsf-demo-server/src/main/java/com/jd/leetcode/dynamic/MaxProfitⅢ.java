package com.jd.leetcode.dynamic;


/**
 * 123. 买卖股票的最佳时机 III
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * <p>
 * 动态规划思路：
 * <p>
 * dp[i][0] : 第一次持有i股票的最大现金流      前一次就持有，或者第一次持有
 * dp[i][1] : 第一次不持有i股票的最大现金流     前一次持有，这次卖；前一次就不持有
 * dp[i][2] : 第二次持有i股票的最大现金流       前一次就是第二次持有了；第二次刚刚买入
 * dp[i][3] : 第二次不持有i股票的最大现金流     前一次就不持有；  第二次刚刚卖
 * <p>
 * 分别取最大值
 * <p>
 * 初始化：都取决于前一次，所以第一天的需要初始化
 * <p>
 * 举例
 */
public class MaxProfitⅢ {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int[][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = -prices[0];
        dp[0][3] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);
        }
        return dp[dp.length-1][3];
    }

}
