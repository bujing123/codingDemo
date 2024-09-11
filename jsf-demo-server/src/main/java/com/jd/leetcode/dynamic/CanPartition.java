package com.jd.leetcode.dynamic;


/**
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 注意: 每个数组中的元素不会超过 100 数组的大小不会超过 200
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * 示例 2:
 * <p>
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 * 提示：
 * <p>
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * <p>
 * <p>
 * 思路：
 * 理解为 在 total/2的背包中，寻找最大可以放的重量，如果可以放下的最大重量是 total/2，那么说明有刚刚好可以一半的
 */
public class CanPartition {


    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //说明不能被2整除，不能平分为两组
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        //在total背包的范围寻找最大的
        int[] dp = new int[target + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], nums[i] + dp[j - nums[i]]);
            }
            // 说明此时已经满足条件了，不需要增加多余的数字去筛选了
            if (dp[target] == target) {
                return true;
            }
        }

        return dp[target] == target;

    }

}
