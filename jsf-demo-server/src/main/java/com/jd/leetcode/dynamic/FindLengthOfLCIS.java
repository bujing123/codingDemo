package com.jd.leetcode.dynamic;


/**
 * 674. 最长连续递增序列
 * <p>
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 * <p>
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，
 * 那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 * <p>
 * <p>
 * 动态思路：
 * <p>
 * dp[j]  到目前位置来说最长子序列
 * dp[j]   if nums[j] > nums[j-1]  dp[j] = dp[j-1] + 1
 * dp[j]  全都定义为1
 * 从小到大遍历
 * 举例
 */
public class FindLengthOfLCIS {
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length < 2) {
            return 1;
        }
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }
        int max = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] > nums[j - 1]) {
                dp[j] = dp[j - 1] + 1;
            }
            max = Math.max(max, dp[j]);
        }
        return max;
    }
}
