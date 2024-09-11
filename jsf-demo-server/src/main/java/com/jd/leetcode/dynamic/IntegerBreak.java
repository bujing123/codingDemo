package com.jd.leetcode.dynamic;


/**
 * 343. 整数拆分
 * <p>
 * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
 * <p>
 * 返回 你可以获得的最大乘积 。
 * <p>
 * <p>
 * 思路：
 * 定义数组 dp[n]  数字n拆分后获得的最大乘积
 * 公式：  dp[n]  比如数字 10 拆分为  5+5 dp[10] = dp[5]*dp[5]  10可以分为 3+7  所以可以  等于3*7或者3*dp[7]
 * 初始化： dp[2] = 1 dp[3] = 2
 * 遍历，i从小到大
 * 举例
 */
public class IntegerBreak {


    public static int integerBreak(int n) {
        if (n == 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        //初始化
        dp[2] = 1;
        dp[3] = 2;
        for (int i = 4; i <= n; i++) {
            int max = 0;
            for (int j = 2; j <= i - 1; j++) {
                max = Math.max(max, j * Math.max(i - j, dp[i - j]));
            }
            dp[i] = max;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        integerBreak(10);
    }


}
