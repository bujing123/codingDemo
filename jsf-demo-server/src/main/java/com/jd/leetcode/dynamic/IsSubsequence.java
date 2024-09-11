package com.jd.leetcode.dynamic;


/**
 * 392. 判断子序列
 * <p>
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * <p>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * <p>
 * 进阶：
 * <p>
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * <p>
 * dp[i][j] : 代表s的前i-1个数，t的前j-1个数的最大子序列
 * dp[i][j] : i和j对应的数据相同时，dp[i][j] = dp[i-1][j-1] + 1  不相同时  从t里面删除一个  dp[i][j] = dp[i][j-1]
 * 初始化为-0
 * i从小到大，j从小到大
 * 举例
 */
public class IsSubsequence {

    public boolean isSubsequence(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return s.length() == dp[s.length()][t.length()];
    }

}
