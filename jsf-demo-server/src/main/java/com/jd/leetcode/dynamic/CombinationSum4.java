package com.jd.leetcode.dynamic;


/**
 * 377. 组合总和 Ⅳ
 * <p>
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * <p>
 * 题目数据保证答案符合 32 位整数范围。
 * <p>
 * <p>
 * 动态规划思路：
 * <p>
 * dp[i][j]:  在前i个数字中，寻找和为 j 的 排列的个数
 * dp[i][j]: 等于 数组中，每个数字作为开头的数量大小  dp[j] = dp[j] + dp[ j - nums[i]]
 * 初始化： dp[0] = 1
 * 遍历顺序： 先遍历背包重量，再遍历数组元素，这样子在遍历背包最大重量的时候，前面都已经排列好了，比如dp[3] = 1作为开头的元素数量 + 2作为元素开头的元素数量 + 3作为开头的元素数量
 * 举例
 */
public class CombinationSum4 {

    public int combinationSum4(int[] nums, int target) {

        int[] dp = new int[target + 1];
        dp[0] = 1;
        //先遍历背包重量
        for (int j = 0; j <= target; j++) {

            //遍历完该背包下的所有元素，证明此时这个背包重量下，已经包含各种元素开头的数据了，比如（1，2）和（2，1）
            for (int i = 0; i < nums.length; i++) {
                //元素比背包更大；
                if (nums[i] > j) {
                    continue;
                }
                dp[j] = dp[j] + dp[j-nums[i]];
            }
        }
        return dp[target];
    }

}
