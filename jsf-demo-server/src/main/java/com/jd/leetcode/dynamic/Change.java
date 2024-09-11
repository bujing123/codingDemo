package com.jd.leetcode.dynamic;


/**
 * 518. 零钱兑换 II
 * <p>
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * <p>
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * <p>
 * 假设每一种面额的硬币有无限个。
 * <p>
 * 题目数据保证结果符合 32 位带符号整数。
 * <p>
 * <p>
 * dp[i][j]：使用前i个硬币，能组成j的最大组合数量
 * dp[j] = dp[j] + dp[j-coins[i]] 使用第i个硬币的情况下，还有多少种可能性来组成
 * 初始化：dp[0] = 0
 * 遍历，从小到大，这样子才能有重复选举的情况
 * 举例
 */
public class Change {

    public int change(int amount, int[] coins) {
        if (amount == 0) return 0;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j-coins[i]];
            }
        }
        return dp[amount];

    }

}
