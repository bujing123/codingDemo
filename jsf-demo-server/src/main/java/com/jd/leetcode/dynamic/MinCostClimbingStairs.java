package com.jd.leetcode.dynamic;


/**
 * 746. 使用最小花费爬楼梯
 * <p>
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。
 * 一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * <p>
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * <p>
 * 请你计算并返回达到楼梯顶部的最低花费。
 * <p>
 * <p>
 * 思路：
 * 定义数组：dp[i] 代表到达第i层阶梯最少需要多少钱
 * 公式推演：dp[i] 应该是第dp[i-1]加上cost[i-1] ,dp[i-2]+cost[i-2]中的更小值
 * 初始化：由于自选从0或者1开始，所以dp[0] = 0; dp[1] = 0;
 * 遍历顺序：从i=2开始，从小到大遍历
 * 举例
 */
public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return Math.min(dp[dp.length - 1] + cost[dp.length - 1], dp[dp.length - 2] + cost[dp.length - 2]);
    }

}
