package com.jd.leetcode.dynamic;


/**
 * 583. 两个字符串的删除操作
 * <p>
 * <p>
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 * <p>
 * 每步 可以删除任意一个字符串中的一个字符。
 * <p>
 * <p>
 * dp[i][j]  代表 word1的 前i个字符和word2的前 j个字符 相同所需要的最小步数
 * dp[i][j]  i和j对应的相同时，不需要删字符 = dp[i-1][j-1]   ；
 * 不相同时，需要删除字符，删除word1或者删除word2，或者两个都删   = min(dp[i-1][j] + 1, dp[i][j-1] + 1, dp[i-1][j-1] + 2)
 * 初始化：i=0时，j要相同就需要删除j个字符，操作为j，j=0时一样
 * i从小到大，j从小到大
 * 举例
 */
public class MinDistance {

    public int minDistance(String word1, String word2) {

        int[][] dp = new int[word1.length() + 1][word2.length()+1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 2, Math.min(dp[i - 1][j], dp[i][j - 1]) + 1);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

}
