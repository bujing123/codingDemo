package com.jd.leetcode.dynamic;


/**
 * 516. 最长回文子序列
 * <p>
 * <p>
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * <p>
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * <p>
 * <p>
 * dp[i][j]: 从i开始，j结尾的字符串，最长回文子序列
 * dp[i][j]:  i和j对应的相同时，dp[i+1][j-1] + 2;  不想同时：max(dp[i+1][j],dp[i][j-1])
 * 初始化：由于没有i和j相同时的赋值，所以提前给i、j相同时赋值为1
 * i从大到小，j从小到大
 * 举例
 */
public class LongestPalindromeSubseq {

    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }


}
