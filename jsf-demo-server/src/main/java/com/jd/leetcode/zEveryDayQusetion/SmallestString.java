package com.jd.leetcode.zEveryDayQusetion;


/**
 * 3106. 满足距离约束且字典序最小的字符串
 * <p>
 * 给你一个字符串 s 和一个整数 k 。
 * <p>
 * 定义函数 distance(s1, s2) ，用于衡量两个长度为 n 的字符串 s1 和 s2 之间的距离，即：
 * <p>
 * 字符 'a' 到 'z' 按 循环 顺序排列，对于区间 [0, n - 1] 中的 i ，计算所有「 s1[i] 和 s2[i] 之间 最小距离」的 和 。
 * 例如，distance("ab", "cd") == 4 ，且 distance("a", "z") == 1 。
 * <p>
 * 你可以对字符串 s 执行 任意次 操作。在每次操作中，可以将 s 中的一个字母 改变 为 任意 其他小写英文字母。
 * <p>
 * 返回一个字符串，表示在执行一些操作后你可以得到的 字典序最小 的字符串 t ，且满足 distance(s, t) <= k 。
 * <p>
 * <p>
 * s思路：
 * 要求字典序尽量小，那么就是从左到右尽量变成 a；变不到a的时候尽量往前变
 */
public class SmallestString {

    public String getSmallestString(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            int b = Math.min(chars[i] - 'a', 26 - chars[i] + 'a');
            if (b < k) {
                k = k-b;
                chars[i] = 'a';
            }else {
                chars[i] = (char) (chars[i] - b);
                break;
            }
        }
        return new String(chars);
    }

}
