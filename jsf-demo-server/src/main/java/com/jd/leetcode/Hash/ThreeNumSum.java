package com.jd.leetcode.Hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * <p>
 * 你返回所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 思路：
 * 由于需要三个索引的数字不同，并且相加起来等于0，而且需要返回不重复的三元组
 * 由于不需要返回下标，因此可以首先排序，排序后，更容易找
 * 排序后：
 * 1、循环第一个指针，从左向右，
 * 2、循环中间指针和右指针分别指着第一个指针的右边，和最后，然后找到满足的数字后，添加进list里面
 * 由于是升序的数字，所以挨着的数字只可能相等，不可能逆向变化，所以可以通过挨着是否相等，来排除重复三元组
 */
public class ThreeNumSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            //没想到的一点
            if (nums[i] > 0) {
                return list;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int mid = i + 1;
            int right = nums.length - 1;
            while (right > mid) {
                int total = nums[i] + nums[mid] + nums[right];
                if (total > 0) {
                    right--;
                } else if (total < 0) {
                    mid++;
                } else {
                    list.add(Arrays.asList(nums[i], nums[mid], nums[right]));
                    for (++mid; mid < right && nums[mid] == nums[mid - 1]; mid++) ;
                    for (--right; right > mid && nums[right] == nums[right + 1]; right--) ;
                }
            }

        }
        return list;
    }


    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i<nums.length-2; i++){
            if(nums[i]>0){
                return list;
            }
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }
            int mid = i+1;
            int right = nums.length-1;
            while(mid<right){
                int total = nums[i] + nums[mid] + nums[right];
                if (total > 0){
                    right--;
                }else if(total < 0){
                    mid++;
                }else {
                    list.add(Arrays.asList(nums[i],nums[mid],nums[right]));
                    for(--right; right>mid&&nums[right]==nums[right+1]; right--);
                    for(++mid; right>mid&&nums[mid]==nums[mid-1]; mid--);
                }
            }
        }
        return list;
    }























}

