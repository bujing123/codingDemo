package com.jd.leetcode.Hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 383. 赎金信
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * <p>
 * 如果可以，返回 true ；否则返回 false 。
 * <p>
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * <p>
 * <p>
 * 思路：
 * 先将magazine字符，每个字母和出现次数放在hashmap里面；然后遍历第一个字符串，每遇到一个字母就去看magazine里面有没有
 * 没有的话直接false，有的话，就把次数-1，然后减下去后如果是负数也是直接false
 */
public class RansomLetter {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] maga = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            maga[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            int c = ransomNote.charAt(i) - 'a';
            maga[c]--;
            if (maga[c] < 0) {
                return false;
            }
        }

        return true;
    }

    public boolean canConstruct2(String ransomNote, String magazine) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            map.put(String.valueOf(magazine.charAt(i)), map.getOrDefault(String.valueOf(magazine.charAt(i)), 0) + 1);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            String a = String.valueOf(ransomNote.charAt(i));
            map.put(a, map.getOrDefault(a, 0) - 1);
            if(map.get(a) < 0){
                return false;
            }
        }
        return true;
    }
}
