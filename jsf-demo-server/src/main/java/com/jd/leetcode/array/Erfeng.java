package com.jd.leetcode.array;

/**
 * 704 二分查找
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
 * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 */
public class Erfeng {
    /**
     * 两种原则 左闭右开  此时右边界设置为比真实数据大1的数组长度，此时右边界无意义，因此每次查询不用处理左右边界相等的情况，因为不会出现这种情况
     *  左闭右闭  此时右边界设置为数组内的数据，此时右边界有意义，因此每次查询需要处理左右边界相等的情况，并且当中间值大于目标值的时候，直接让右边界为中间值左边的数据
     * @param nums
     * @param target
     * @return
     */

    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length;
        while (start < end) {
            int mid = start + (end-start) / 2;
            if (nums[mid] < target){
                start = mid+1;
            }else if(nums[mid] == target){
                return mid;
            }else {
                end = mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,3,5,9,12};
        int[] nums1 = new int[]{2,5};

        int target = 9;
    }
}