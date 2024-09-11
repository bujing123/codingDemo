package com.jd.leetcode.dynamic;


/**
 * 494. 目标和
 * <p>
 * 给你一个非负整数数组 nums 和一个整数 target 。
 * <p>
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * <p>
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * <p>
 * <p>
 * <p>
 * 相当于把 nums的所有数，分成两个集合，，left - right = target ；并且此时left + right = nums.sum
 * 所以 left = (target + nums.sum) / 2  ; right = (nums.sum - target) / 2 = nums.sum - left
 * 因此只需要找到 从 nums 中凑出 left 的 次数即可
 * <p>
 * 定义：dp[j] : 数组定义为 包裹重量为 j 的次数最多是多少 j最大值为left
 * 公式：dp[j] = dp[j] + dp[j-nums[i]] 累加     和为j的次数等于：算上此时第i个元素+不算是第i个元素的值；算上：dp[j-nums[i]];不算是：dp[j]
 * 初始化：dp[0] = 1
 * 遍历顺序：i从小到大 j从大到小
 */
public class FindTargetSumWays {

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //此时left为小数，不能被nums的数凑出来
        if ((sum + target) % 2 != 0) return 0;
        if (Math.abs(target) > sum) return 0;

        int left = Math.abs(sum + target) / 2;
        int[] dp = new int[left + 1];
        dp[0] = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = left; j >= nums[i]; j--) {
                //每次
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[left];

    }


}
