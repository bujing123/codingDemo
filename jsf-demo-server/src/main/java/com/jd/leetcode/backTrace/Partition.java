package com.jd.leetcode.backTrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. 分割回文串
 * <p>
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串
 * 返回 s 所有可能的分割方案。
 * <p>
 * <p>
 * 思路：
 * 1、终止条件：分割完毕，并且每个串都是回文串
 * 2、每一轮递归的逻辑：分割剩下的字符串
 */
public class Partition {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backTrace(s,0,new ArrayList<>());
        return res;
    }

    void backTrace(String s, int start, List<String> list) {
        if (start == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String substring = s.substring(start, i + 1);
            if (ifPalindRome(substring)) {
                list.add(substring);
            } else {
                continue;
            }
            backTrace(s, i + 1, list);
            list.remove(list.size()-1);

        }
    }

    boolean ifPalindRome(String s) {
        for (int start = 0, end = s.length() - 1; start < end; start++, end--) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aaaa";
        System.out.println(s.substring(0, 3));
    }

}
