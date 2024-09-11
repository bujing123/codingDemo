package com.jd.leetcode.dynamic;


/**
 * 322. 零钱兑换
 * <p>
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * <p>
 * <p>
 * 思路：
 * dp[j]: 金额为 j 所需要的最小硬币数量
 * 公式:  dp[j] : Math.min ( dp[j], 1+dp[j-coins[i]] )   每次判断能满足j的最少硬币，取最小值
 * 初始化 dp[0] = 0
 * 遍历顺序：先遍历背包大小，再遍历硬币数组
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        //先遍历物品
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    //说明不能由当前的硬币来合成
                    dp[j] = Math.min(dp[j], 1 + dp[j - coins[i]]);
                }
            }
        }

        //先遍历背包
        for (int j = 0; j <= amount; j++) {
            for (int i = 0; i < coins.length; i++) {
                //等于最大值说明不能被硬币填满
                if (coins[j] > j || dp[j] == Integer.MAX_VALUE) {
                    continue;
                }
                dp[j] = Math.min(dp[j], 1 + dp[j - coins[i]]);
            }
        }


        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }


}
