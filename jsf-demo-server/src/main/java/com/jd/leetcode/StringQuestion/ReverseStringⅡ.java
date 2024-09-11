package com.jd.leetcode.StringQuestion;

/**
 * 541. 反转字符串 II
 * <p>
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 * <p>
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * <p>
 * <p>
 * 思路：
 * 相当于每隔2k个就反转前k个，如果后面不到k个就全部反转
 * 因此，每次循环，直接反转起点，相当于0 2k 4k；结尾相当于   start+k-1 和 s.length-1取最小值来进行反转
 * 知道这个每次循环的起点和终点就好写了
 */
public class ReverseStringⅡ {
    public String reverseStr(String s, int k) {
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length() - 1; i += 2 * k) {
            int start = i;
            int end = Math.min(s.length() - 1, start + k - 1);
            reverse(c,start,end);
        }
        return new String(c);
    }

    private void reverse(char[] c, int start, int end) {
        while (start < end) {
            c[start] ^= c[end];
            c[end] ^= c[start];
            c[start] ^= c[end];
            start++;
            end--;
        }
    }
}
