package com.jd.leetcode.greedy;


/**
 * 55. 跳跃游戏
 * <p>
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 思路：在每次的跳跃最大范围内，找到下一次能跳到的最大范围
 * 假设：nums：【1，2，3，4，5】
 * 从第一步来看，最大能走到2；于是从2来看，最大能走到4，于是，每次都在能走得覆盖范围内，找最远的能走到的位置，最后循环完，看覆盖范围是否达到最大值
 */
public class CanJump {

    public boolean canJump(int[] nums) {
        if (nums.length < 2) return true;
        int max = nums[0];
        for (int i = 0; i <= max && i < nums.length; i++) {
            if (nums[i] + i > max){
                max = nums[i] + i;
            }
        }
        return max>=nums.length-1;
    }

}
