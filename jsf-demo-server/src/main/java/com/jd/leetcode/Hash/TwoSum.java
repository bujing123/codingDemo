package com.jd.leetcode.Hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *
 * 本题其实有四个重点：
 *
 * 为什么会想到用哈希表   :   当需要判断一个数字是否存在集合里面，就需要使用hash
 * 哈希表为什么用map：      当有两个关联的，比如本题需要数字和下标，因此使用hashmap
 * 本题map是用来存什么的 ::       一个是数字，一个是需要返回的下标值
 * map中的key和value用来存什么的  ：  数字是用来判断有没有符合数字之和的值，value存储下标是答案需要
 *
 *
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map  = new HashMap<>(); //key存储值，value存储下标
        for (int i=0; i<nums.length; i++) {
            int num = nums[i];
            int offest = target-num;
            if(map.containsKey(offest)){
                return new int[]{i,map.get(offest)};
            }
            if(map.containsKey(num)){
                continue;
            }
            map.put(num,i);
        }
        return null;
    }

    public int[] twoSum2(int[] nums, int target){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i<nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(target-num)){
                return new int[]{map.get(target-num),i};
            }
            map.put(num,i);
        }
        return null;
    }
}
