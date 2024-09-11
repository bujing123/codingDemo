package com.jd.leetcode.dynamic;


/**
 * 718. 最长重复子数组
 * <p>
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 * <p>
 * <p>
 * dp[i][j]  :  代表  nums1 的i-1 和 nums2 的j-1 的位置处，最长重复子数组长度
 * dp[i][j]  i和j对应的数据相同时  dp[i][j]  =  dp[i-1][j-1] + 1
 * dp[i][j] 初始化：i=0时，j=0时，相同的位置赋值为1
 * i逐渐变大，j逐渐变大
 * 举例
 */
public class FindLength {

    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length][nums2.length];

        int max = 0;
        for (int j = 0; j < nums2.length; j++) {
            if (nums1[0] == nums2[j]) {
                dp[0][j] = 1;
                max = 1;
            }
        }
        for (int i=0; i<nums1.length; i++){
            if (nums1[i] == nums2[0]) {
                dp[i][0] = 1;
                max = 1;
            }
        }


        for (int i = 1; i < nums1.length; i++) {
            for (int j = 1; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }


}
