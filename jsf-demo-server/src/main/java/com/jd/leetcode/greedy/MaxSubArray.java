package com.jd.leetcode.greedy;

/**
 * 53. 最大子数组和
 * <p>
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 子数组是数组中的一个连续部分。
 * <p>
 * 思路：
 * 滑动窗口，当前数字变为负数时，直接放弃
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int max = nums[0];//记录最大和
        int count = 0; //记录到当前的和，如果变为负数直接舍弃
        for (int num : nums) {
            count += num;
            max = Math.max(max, count);
            if (count < 0) {
                count = 0;
            }
        }
        return max;

    }

    /**
     * 动态规划思路：
     */
    public int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
