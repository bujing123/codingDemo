package com.jd.leetcode.zEveryDayQusetion;

import java.util.Arrays;

/**
 * 把数组分成两个数组，让数组1的最大值、数组2的最小值的差值最小
 */
public class ZEveryDayQuestion {

    public int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        for (int i=1; i<nums.length; i++){
            min = Math.min(nums[i] - nums[i-1], min);
        }

        return min;
    }
}
