package com.jd.leetcode.backTrace;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * 78. 子集
 *
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集 (幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 */
public class SubSets {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backTrace(new ArrayList<>(), 0, nums);
        return res;
    }

    void backTrace(List<Integer> list, int start, int[] nums){
        res.add(new ArrayList<>(list));
        if(start == nums.length){
            return;
        }
        for (int i=start; i<nums.length; i++){
            list.add(nums[i]);
            backTrace(list,i+1,nums);
            list.remove(list.size()-1);
        }
    }

}
