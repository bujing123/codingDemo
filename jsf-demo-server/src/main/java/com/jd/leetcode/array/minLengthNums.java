package com.jd.leetcode.array;

/**
 * 209. 长度最小的子数组
 * <p>
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其总和大于等于 target 的长度最小的 连续
 * 子数组
 * [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 */
public class minLengthNums {
    public static int minSubArrayLen(int target, int[] nums) {
        if (nums[0] >= target) {
            return 1;
        }
        if (nums.length < 2) {
            return 0;
        }
        int index1 = 0, index2 = 1;
        int total = nums[index1] + nums[index2];
        int length = nums.length+1;
        while (index1 < nums.length) {
            if (total >= target ) {
                length = Math.min(length, index2-index1+1);
                total = total-nums[index1];
                index1++;
                continue;
            }
            if (total < target && index2 < nums.length-1){
                index2++;
                total = total + nums[index2];
                continue;
            }
            if (length == nums.length+1){
                return 0;
            }else {
                return length;
            }
        }
        return length;
    }

    public static void main(String[] args) {
        int target = 7;
        int[] nums = new int[]{2,3,1,2,4,3};
        minSubArrayLen(7,nums);
    }
}
