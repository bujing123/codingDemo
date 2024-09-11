package com.jd.leetcode.dynamic;


/**
 * 1035. 不相交的线
 * <p>
 * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
 * <p>
 * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足：
 * <p>
 * nums1[i] == nums2[j]
 * 且绘制的直线不与任何其他连线（非水平线）相交。
 * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
 * <p>
 * 以这种方法绘制线条，并返回可以绘制的最大连线数。
 * <p>
 * <p>
 * 动态规划： 其实就是看可以不相邻重复子数组长度
 * dp[i][j] nums1取0，i-1；nums2取 0,j-1
 * dp[i][j] 如果i-1和j-1对应的值一样，=dp[i-1][j-1] +1 不相等，则 max(dp[i-1][j], dp[i][j-1])
 * 初始化为0
 * i从小到大，j从小到大
 */
public class MaxUncrossedLines {

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }

}
