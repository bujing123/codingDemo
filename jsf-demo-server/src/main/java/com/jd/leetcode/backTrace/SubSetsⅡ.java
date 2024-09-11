package com.jd.leetcode.backTrace;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. 子集 II
 * <p>
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 */
public class SubSetsⅡ {

    List<List<Integer>> res = new ArrayList<>();
    boolean[] used;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        used = new boolean[nums.length];
        backTrace(new ArrayList<>(), nums, 0);
        return res;
    }

    void backTrace(List<Integer> list, int[] nums, int start) {
        res.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i-1]) {
                continue;
            }
            list.add(nums[i]);
            used[i] = true;
            backTrace(list, nums, i + 1);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

}
