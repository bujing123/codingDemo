package com.jd.leetcode.dynamic;


import java.util.HashSet;
import java.util.List;

/**
 * 139. 单词拆分
 * <p>
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 * <p>
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * <p>
 * <p>
 * dp[j] true false 代表 代表从0到j能不能被字母数集所覆盖
 * dp[j]  dp[i] && wordDict.contains( s.substring(i,j))  当从i到j这一串字母数据在 字母集合中有，并且前面的一串也有，那说明此时为true
 * dp[0] true
 * 先遍历背包，再遍历数组  因为，可能apple pen apple  需要遍历两次apple；所以把数组的放内部遍历
 * 举例
 */
public class WordBreak {


    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        HashSet<String> set = new HashSet<>(wordDict);
        dp[0] = true;

        for (int j = 1; j <= s.length(); j++) {
            for (int i = 0; i < j; i++) {
                if (set.contains(s.substring(i, j)) && dp[i]) {
                    dp[j] = true;
                }
            }
        }
        return dp[s.length()];

    }


}
