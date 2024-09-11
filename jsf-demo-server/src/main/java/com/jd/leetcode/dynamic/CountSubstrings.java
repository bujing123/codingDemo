package com.jd.leetcode.dynamic;


/**
 * 647. 回文子串
 * <p>
 * <p>
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * <p>
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * <p>
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * <p>
 * <p>
 * dp[i][j]  代表，i和j对应的区间内，是否是回文子串
 * dp[i][j]  i和j对应的相同时，如果j和i中间还有其他值，就看中间是否是回文子串，如果中间的数量没有或者是同一个，那说明也是回文子串；
 * dp[i][j] 都定义为false
 * i从大到小，j从小到大
 * 举例
 */
public class CountSubstrings {

    public int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        //记录回文子串的数量
        int result = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                //相同
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) {
                        result++;
                        dp[i][j] = true;
                    } else {
                        if (dp[i + 1][j - 1]) {
                            result++;
                            dp[i][j] = true;
                        }
                    }

                }else {
                    //不相同，说明不是回文子串，跳过
                }
            }
        }

        return result;
    }


}
