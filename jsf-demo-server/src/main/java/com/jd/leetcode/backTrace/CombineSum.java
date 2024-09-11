package com.jd.leetcode.backTrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和 III
 * <p>
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * <p>
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 * <p>
 * 思路：
 * 第一步：循环的终止条件，找到符合要求的数据，已经有k个数并且和是n
 * 第二步：每一次循环需要做得逻辑，这个题目里面就是每次循环都要继续从剩余的数据中找数据去符合要求
 * 每次循环可以看做：终止条件，处理当前节点，递归下一次，回溯处理
 */
public class CombineSum {
    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> combinationSum3(int k, int n) {
        if (n > 45 || n < 3) {
            return res;
        }
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        backTrace(list, sum, k, n, 1, 9);
        return res;

    }

    /**
     * @param list  递归中存储已经递归过的数据
     * @param sum   list里面存储的数据的和
     * @param k     要求的节点个数
     * @param n     要求的节点之和
     * @param left  当前已经递归过的最小数据
     * @param right 最大数据
     */
    static void backTrace(List<Integer> list, int sum, int k, int n, int left, int right) {
        if (list.size() == k && sum == n) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (sum + left > n || list.size() > k) {
            return;
        }
        for (int i = left; i <= right; i++) {
            sum += i;
            list.add(i);
            backTrace(list, sum, k, n, i + 1, right);
            list.remove(list.size() - 1);
            sum -= i;
        }
    }

    public static void main(String[] args) {
        combinationSum3(3, 7);
    }

}
