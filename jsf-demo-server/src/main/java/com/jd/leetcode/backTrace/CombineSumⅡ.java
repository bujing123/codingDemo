package com.jd.leetcode.backTrace;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II
 * <p>
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，
 * 找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * <p>
 * 注意：解集不能包含重复的组合。
 * <p>
 * 思路：
 * 数组candidates有重复元素，而要求不能有重复的组合
 * 因此如果连续两个字符,在同一树层下就不需要递归了；
 *
 * 因此定义：boolean[] used ;  在递归树深度中，他是true；回溯回来后又改回false，这样子同一树层下就可以判断他来判断是否需要递归
 * */
public class CombineSumⅡ {

    List<List<Integer>> res = new ArrayList<>();
    boolean[] used;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        used = new boolean[candidates.length];
        if (candidates == null || candidates.length < 1) {
            return res;
        }
        Arrays.sort(candidates);
        backTrace(new ArrayList<>(), 0, candidates, target, 0);
        return res;
    }

    void backTrace(List<Integer> list, int sum, int[] candidates, int target, int start) {
        if (sum == target) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            int candidate = candidates[i];
            list.add(candidate);
            sum += candidate;
            backTrace(list, sum, candidates, target, i + 1);
            used[i] = false;
            sum -= candidate;
            list.remove(list.size() - 1);
        }
    }

}
