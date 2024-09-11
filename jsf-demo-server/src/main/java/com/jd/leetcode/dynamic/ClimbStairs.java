package com.jd.leetcode.dynamic;


/**
 * 70. 爬楼梯
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 思路:
 * 定义dp公式：dp[n]代表到第n层的办法；
 * 递推公式：dp[n] 相当于从第n-1层到第n层有1种办法；第n-2层到n层有一种办法；
 * 初始化：dp[0] = 0 ;dp[1] = 1
 * 遍历顺序：从n=2开始遍历
 */
public class ClimbStairs {

    /**
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n < 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];

    }


    /**
     * 如果一次是 1-m之间个楼梯
     *
     *
     * 达到dp[j]层的次数 等于 从 i-j层一下子跳i格 i在1到m之间
     */
    public int climbStairs(int n, int m) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int j = 0; j <= n; j++) {
            //从1到m层
            for (int i=1; i<=m; i++){
                if (j-i<0){
                    //目的地比一步走的路还少，不需要
                    continue;
                }
                dp[j] = dp[j] + dp[j-i];
            }
        }
        return dp[n];
    }

}
