package com.jd.leetcode.dynamic;


/**
 * 474. 一和零
 * <p>
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * <p>
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * <p>
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * <p>
 * <p>
 * <p>
 * 思路：
 * dp[i][j]: 最多有i个0，j个1时；最大子集长度
 * 公式：dp[i][j] = 1 + dp[i-zeroNum][j-oneNum] 或者dp[i][j]取最大值
 * 初始化：dp[0][0] = 0
 * 遍历顺序 i 和 j 都从大到小；从小到大的话，可能会有重复选数  比如有个字符为 '1'  i=0,j=3时，从小到大选会选出3个'1'
 * *
 */
public class FindMaxForm {

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int zeroNum = 0;
            int oneNum = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    zeroNum++;
                } else {
                    oneNum++;
                }
            }
            for (int i = m; i>=zeroNum; i--) {
                for (int j = n; j>=oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], 1+dp[i-zeroNum][j-oneNum]);
                }
            }
        }
        return dp[m][n];
    }
}
