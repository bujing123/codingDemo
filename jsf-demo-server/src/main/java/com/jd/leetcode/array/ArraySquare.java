package com.jd.leetcode.array;

/**
 * 977.有序数组的平方
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * 给定数组为升序排序，但有可能为负数，说明数组的当前平方最大值，只可能在最左边和最右边，因此使用相向双指针解决
 */
public class ArraySquare {
    public int[] sortedSquares(int[] nums) {
        int left = 0, right = nums.length - 1, index = nums.length - 1;
        int[] result = new int[nums.length];
        while (index >= 0) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            if (leftSquare < rightSquare) {
                result[index--] = rightSquare;
                right--;
            } else {
                result[index--] = leftSquare;
                left++;
            }
        }
        return result;
    }
}
