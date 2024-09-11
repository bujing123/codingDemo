package com.jd.leetcode.greedy;


import java.util.ArrayList;
import java.util.List;

/**
 * 763. 划分字母区间
 *
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 *
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 *
 * 返回一个表示每个字符串片段的长度的列表。
 *
 *
 * 贪心思想：
 * 局部最优：每次都看当前节点的最后一次出现位置；如果已经是区间内最大的最后一次出现位置，那么划分区间
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String s) {
        int[] hash = new int[26];
        //标记每个字母出现的最后一个位置
        for (int i=0; i<s.length(); i++){
            hash[s.charAt(i)-'a'] = i;
        }
        int preIndex = -1;
        int max = 0;
        List<Integer> list = new ArrayList<>();
        //从第一个位置开始
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            max = Math.max(max, hash[c-'a']);
            //最大位置是当前位置，划分区域
            if (max == i){
                list.add(i-preIndex);
                preIndex = i;
            }
        }
        return list;
    }
}
