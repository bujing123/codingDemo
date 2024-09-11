package com.jd.leetcode.backTrace;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 491. 非递减子序列
 * <p>
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。
 * 你可以按 任意顺序 返回答案。
 * <p>
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 * <p>
 * 思路：
 * 1、终止条件，list的大小大于2就放进响应结果中
 * 2、需要注意的点：
 * 不能有重复的，因此需要，树层不能有重复的；
 * 需要start来定义每次被选择的位置
 * <p>
 * <p>
 * 注意：
 * 深度是使用递归方法；广度是使用的for循环去搜索
 */
public class FindSubsequences {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backTrace(nums, 0);
        return res;
    }


    /**
     * 之所以不像前面那样子使用 used 数组，是因为这个是无序的数组，可以直接  boolean[] used = new boolean[201] 因为num的大小只在两百以内
     */
    void backTrace(int[] nums, int start) {
        if (list.size() > 1) {
            res.add(new ArrayList<>(list));
        }
        Set<Integer> set = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (list.size() != 0 && nums[i] < list.get(list.size() - 1)) {
                continue;
            }
            if (set.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            set.add(nums[i]);
            backTrace(nums, i + 1);
            list.remove(list.size() - 1);

        }

    }


    void backTrace2(int[] nums, int start) {
        if (list.size() > 1) {
            res.add(new ArrayList<>(list));
        }
        Set<Integer> set = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            if (list.size() != 0 &&
                    nums[i] < list.get(list.size() - 1)) {
                continue;
            }
            list.add(nums[i]);
            set.add(nums[i]);
            backTrace(nums, i + 1);
            list.remove(list.size() - 1);

        }

    }
}
