package com.jd.leetcode.backTrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 39. 组合总和
 * <p>
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
 * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
 * 并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 */
public class CombineSumⅠ {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        backTrace(new ArrayList<>(), 0, target, candidates, 0);
        return res;
    }

    /**
     * 单个集合求组合，需要start位置来控制每一层for循环的起始位置，这样子来避免顺序不同导致的选择不同
     * 多个集合求组合，由于此时每个集合只会选择一次，因此不需要start位置
     * @param list
     * @param sum
     * @param target
     * @param candidates
     */
    void backTrace(List<Integer> list, int sum, int target, int[] candidates, int start) {
        if (sum == target) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i= start; i<candidates.length; i++){
            int candidate = candidates[i];
            sum += candidate;   //控制每次的和
            list.add(candidate);   //控制每次增加和以后的元素
            backTrace(list,sum,target,candidates,i);   //递归遍历
            sum -= candidate;       //递归后回溯减去元素
            list.remove(list.size()-1);  //此时list里面也删除
        }

    }

}
