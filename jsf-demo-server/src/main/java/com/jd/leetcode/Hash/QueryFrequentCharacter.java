package com.jd.leetcode.Hash;

import java.util.ArrayList;
import java.util.List;

/**
 * 1002. 查找共用字符
 * 给你一个字符串数组 words ，请你找出所有在 words 的每个字符串中都出现的共用字符（ 包括重复字符）
 * ，并以数组形式返回。你可以按 任意顺序 返回答案。
 *
 * 思路：
 * 先将第一个字符出现的字母次数记录
 * --->从第二个开始循环，记录每一个字符其中字母出现次数
 * --->每次循环中，都拿出现的次数，和第一个的记录，取最小出现次数，因为最小出现次数，就是他们的共用字符
 * --->最后根据数组记录的次数，下标就是字母，值就是出现次数，把每个字符放到list里面
 */
public class QueryFrequentCharacter {
    public List<String> commonChars(String[] words) {
        //使用一个数组来存储每个字母出现的最低次数
        int[] minHash = new int[26];
        for (int i = 0; i < words[0].length(); i++) {
            minHash[words[0].charAt(i) - 'a']++;
        }

        for (int j = 1; j < words.length; j++) {
            String word = words[j];
            int[] hash = new int[26];
            for (int i = 0; i < word.length(); i++) {
                hash[word.charAt(i)-'a']++;
            }
            for (int k = 0; k<26; k++){
                minHash[k] = Math.min(hash[k],minHash[k]);
            }
        }

        List<String> list = new ArrayList<>();
        for(int k = 0; k<26; k++){
            while(minHash[k]-- > 0){
                char c = (char)(k+'a');
                list.add(String.valueOf(c));
            }
        }
        return list;
    }

}
