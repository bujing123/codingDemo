package com.jd.leetcode.dynamic;


/**
 * 213. 打家劫舍 II
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 * <p>
 * <p>
 * 思路：
 * dp[j] 代表到前几个屋子能偷到的最高价值
 * dp[j] : max(dp[j-1], dp[j-2]+value[j])  因为首尾相连，所以从尾部和首部，分成两个数组分别计数，最后取两个的更大值
 * dp[0] = nums[0] dp[1] = nums[1]
 * 从小到大
 * 举例
 */
public class RobⅡ {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2){
            return Math.max(nums[0], nums[1]);
        }

        //从 0 开始，length-1结束，不看最后一个楼
        int[] dp1 = new int[nums.length - 1];
        dp1[0] = nums[0];
        dp1[1] = Math.max(nums[0], nums[1]);
        for (int j = 2; j <= nums.length - 2; j++) {
            dp1[j] = Math.max(dp1[j - 1], dp1[j - 2] + nums[j]);
        }

        //从1开始，最后一个结束，不看第一个楼
        int[] dp2 = new int[nums.length];
        dp2[0] = 0; //不偷第一个
        dp2[1] = nums[1];
        for (int j = 2; j <= nums.length - 1; j++) {
            dp2[j] = Math.max(dp2[j - 1], dp2[j - 2] + nums[j]);
        }

        return Math.max(dp1[dp1.length - 1], dp2[dp2.length - 1]);
    }


}
