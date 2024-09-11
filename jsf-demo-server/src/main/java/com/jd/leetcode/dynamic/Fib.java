package com.jd.leetcode.dynamic;


/**
 * 509. 斐波那契数
 * <p>
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 */
public class Fib {

    /**
     * 递归
     * @param n
     * @return
     */
    public int fib2(int n) {
        if (n == 1 || n == 0) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 动态规划
     * 第一步：确认dp数组的含义：dp[i] 代表数字为n时，斐波那契数列的值
     * 第二步：确认递归公式： dp[i] = dp[i-1] + dp[i-2];
     * 第三步：初始化：  dp[0] = 0; dp[1] = 1
     * 第四步：确认遍历顺序：从n=2开始，遍历到n即可
     * 第五步，选择一个值去举例，选n=5去举例
     */
    public int fib(int n){
        if (n<2){
            return n;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i=2; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
