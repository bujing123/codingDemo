package com.jd.leetcode.dynamic;


/**
 * 72. 编辑距离
 * <p>
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * <p>
 * dp[i][j] : 代表 i-1 和 j-1的前那么多个字符，需要转换的最少操作数
 * dp[i][j]:  i和j对应的字符相同时，说明不需要操作  = dp[i-1][j-1]
 * 不同时，需要增删改  增相当于word2删，所以    == min(dp[i-1][j] + 1, dp[i][j-1] + 1, dp[i-1][j-1] + 1)
 * 初始化：j或者i等于0时，需要删除i个字符或者j个字符
 * i和j都从小到大
 * 举例
 */
public class MinDistanceReplace {

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
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
                    //修改字符，和删除word1的字符和删除word2的字符相比，取最小的
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j], dp[i][j - 1]) + 1);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

}
