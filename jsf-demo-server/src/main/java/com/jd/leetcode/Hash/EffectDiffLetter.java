package com.jd.leetcode.Hash;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 */
public class EffectDiffLetter {
    public boolean isAnagram(String s, String t) {
        int[] nums = new int[26];
        for (int i = 0; i<s.length(); i++){
            nums[s.charAt(i) - 'a']++;
        }
        for (int i =0; i<t.length(); i++){
            nums[t.charAt(i)-'a']--;
        }
        for (int num : nums) {
            if(num != 0){
                return false;
            }
        }
        return true;
    }
}
