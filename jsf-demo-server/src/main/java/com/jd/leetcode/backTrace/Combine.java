package com.jd.leetcode.backTrace;


import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * <p>
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案。
 *
 * 思路：
 * 第一步：递归终止条件：list里面已经包含k个数了
 * 第二步：确定每层循环的逻辑：对于还没看到的数字进行循环
 * 第三步：
 */
public class Combine {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (n < 1) {
            return res;
        }
        List<Integer> list = new ArrayList<>();
        backTrace(list, k, 1, n);
        return res;
    }


    void backTrace(List<Integer> list, int k, int left, int right) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        // 剪枝操作1，当需要的集合只剩一个数字的时候，直接一个for循环把所有list搞到，不用浪费空间时间再去递归
        if (k - list.size() == 1) {
            for (int j = left; j <= right; j++) {
                list.add(j);
                res.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
            }
            return;
        }
        // 剪枝操作1 end
        for (int i = left; i <= right; i++) {
            // 剪枝操作2，当可以选的数字全部选上，也不够list的大小和需要的大小一样时，就没必要继续往下递归了
            if (list.size() + right - i + 1 < k) {
                break;
            }

            list.add(i);
            backTrace(list, k, i + 1, right);
            list.remove(list.size() - 1);
        }
    }

}
