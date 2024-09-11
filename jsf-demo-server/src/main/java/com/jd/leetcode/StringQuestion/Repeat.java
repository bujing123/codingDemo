package com.jd.leetcode.StringQuestion;

/**
 * 459. 重复的子字符串
 * 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
 * <p>
 * 思路：
 * 1、先搞到前缀表next数组
 * 2、根据前缀表next数组拿到最后一个循环小组，根据最后一个循环小组的长度，如果可以被原本字符串长度所除尽，说明刚刚好能被重复构成
 *
 *
 * 两个重要点位：
 * 1、如果存在一个小子串，那他必然满足前缀子串和后缀子串相等，前缀表的最后一个循环小组就是最小后缀子串（最后一个值对应的下标，在这个下标后边的小组被我称为后缀子串）
 * 2、后缀的小子串可以重复往前覆盖其他值
 */
public class Repeat {

    public static boolean repeatedSubstringPattern(String s) {
        int[] next = getNext(s);
        //最后一个循环小组长度：
        int i = s.length() - next[s.length() - 1];

        if (next[s.length() - 1] > 0 && s.length() % i == 0) {
            //说明循环小组被整除，说明字符串刚刚好能够被循环小组重复多次构成  重复数量为s.length()/i
            return true;
        }
        return false;
    }

    /**
     * 得到前缀数组
     *
     * @param s
     * @return
     */
    private static int[] getNext(String s) {
        int[] next = new int[s.length()];
        int j = 0;
        next[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(j) != s.charAt(i)) {
                j = next[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static void main(String[] args) {
        repeatedSubstringPattern("abac");
    }
}
