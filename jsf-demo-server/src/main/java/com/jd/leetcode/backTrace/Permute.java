package com.jd.leetcode.backTrace;


import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 * <p>
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * 思路：
 * 1、终止：list里面的长度和nums长度一样，说明添加完毕；
 * 2、每次循环从起始点开始，然后判断list里面是否已经包含
 */
public class Permute {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    /**
     * 因为每次递归中，没有开始节点，用used来定义在每个树深中第 i 个节点是否已经使用过；如果在深度挖掘中，发现已经使用过，则跳过
     */
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        backTrace(nums);
        return res;
    }

    void backTrace(int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            list.add(nums[i]);
            backTrace(nums);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

}
