package com.jd.leetcode.dynamic;


/**
 * 279. 完全平方数
 * <p>
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * <p>
 * 完全平方数 是一个整数，其值等于另一个整数的平方；
 * 换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * <p>
 * <p>
 * 思路：
 * dp[j]:j数组最小由几个构成
 * dp[j] = min (dp[j-i*i] + 1, dp[j])
 * dp[0] = 0 dp[1] = 1 全部初始化为本身
 * 遍历顺序：i从小到大 j从小到大
 * 举例
 */
public class NumSquares {


    public static int numSquares(int n) {
        int[] dp = new int[n + 1];

        //初始化为全部用1组成的数字
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }

        //  先物品，再背包，背包要使用i必须大于i*i，因此从i*i开始遍历
        for (int i = 2; i <= n; i++) {
            for (int j = i * i; j <= n; j++) {
                dp[j] = Math.min(dp[j - i * i] + 1, dp[j]);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        numSquares(13);
    }


}
