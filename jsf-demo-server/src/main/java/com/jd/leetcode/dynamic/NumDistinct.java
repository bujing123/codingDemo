package com.jd.leetcode.dynamic;


/**
 *
 * 115. 不同的子序列
 *
 * 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数，结果需要对 109 + 7 取模。
 *
 *
 * dp[i][j] ：代表 s的0，i-1的字符串中 t的 0，j-1的出现个数
 * dp[i][j] : i-1和j-1对应的相同时，说明此时可以使用s的第i-1个数据，那么此时 = dp[i-1][j-1]，如果选择不使用，那么 = dp[i-1][j] 因此两个相加就是结果；如果不相等，说明用不了
 * 初始化：i等于0时，说明从空字符串找符合t的，那肯定找不到，为0；j等于0时，说明从s中能够变为空字符串的次数，那么全部删除就可以；为1
 * i和j分别从小到大
 * 举例
 *
 */
public class NumDistinct {

    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        // 初始化 从s中找空字符串
        for (int i=0; i<=s.length(); i++){
            dp[i][0] = 1;
        }
        for (int i=1; i<=s.length(); i++){
            for (int j=1; j<=t.length(); j++){
                if (s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[s.length()][t.length()];



    }
}
