package com.jd.leetcode.array;

/**
 * 27.移除元素
 */
public class DeleteElement {
    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * 说明:
     * 为什么返回数值是整数，但输出的答案是数组呢?
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     * 你可以想象内部操作如下:
     * nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
     * int len = removeElement(nums, val);
     * 在函数里修改输入数组对于调用者是可见的。
     * 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
     * for (int i = 0; i < len; i++) {
     * print(nums[i]);
     * }
     */

    public int removeElement(int[] nums, int val) {
        /**
         * 使用双指针，快指针指到不是给定值的时候，将快指针指着的值赋值给慢指针指着的值
         */
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if(nums[fast] != val){
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;

    }
}
