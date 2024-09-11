package com.jd.leetcode.dynamic;


/**
 * 300. 最长递增子序列
 * <p>
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列
 * <p>
 * <p>
 * dp[j] : 表示到当前位置来说最长的子序列
 * dp[j] : 如果新的位置比前面的更大，那么   = max(dp[i], dp[j]+1)
 * dp[j]  全部定义为1
 * 从小到大
 * 举例
 */
public class LengthOfLIS {

    public int lengthOfLIS(int[] nums) {
        if (nums.length < 2) {
            return 1;
        }

        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }
        int max = 0;

        //这个是定义为每个窗口的终点
        for (int i = 1; i < nums.length; i++) {

            //定义为起点
            for (int j = 0; j < i; j++) {

                //用起点和终点进行判断，如果更大，说明至少也是起点的数据+1
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }

}
