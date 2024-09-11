package com.jd.leetcode.dynamic;


/**
 * 62. 不同路径
 * <p>
 * <p>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * <p>
 * 问总共有多少条不同的路径？
 * <p>
 * <p>
 * 思路：
 * 定义dp数组 dp[i][j] 代表到第i行j列有多少种方法
 * 公式递推 dp[i][j] 可以从dp[i-1][j] 或者 dp[i][j-1]移动而得
 * 初始化：dp[0][0] = 0
 * 遍历： i逐渐变大，j逐渐变大
 * 举例
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] dp = new int[m][n];
        //初始化，把第一行第一列都定义为1；
        for (int i=0; i<m; i++){
            dp[i][0] = 1;
        }
        for (int j=0; j<n; j++){
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (i == 0) {
                    dp[i][j] = dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

}
