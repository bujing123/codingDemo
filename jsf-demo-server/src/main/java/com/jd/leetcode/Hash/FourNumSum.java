package com.jd.leetcode.Hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 * <p>
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
 * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * <p>
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * <p>
 * 思路：
 * 1、不需要下标，首先排序
 * 为三数之和的进阶版  三数之和先确定一个数字，然后双指针来确认和；
 * 这个就先确认两个数字，然后双指针来找；以此类推，无数之和都可以靠这个办法来降低时间复杂度
 */
public class FourNumSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int left = i + 1; left < nums.length - 2; left++) {
                if (nums[i] + nums[left] > target && nums[left] > 0) {   //此时二数之和已经大于目标值了，再加只会越来越大，因此直接跳出
                    break;
                }
                if(left>i+1 && nums[left] == nums[left-1]){
                    continue;
                }
                int mid = left + 1;
                int right = nums.length - 1;
                while (mid < right) {
                    int total = nums[i] + nums[left] + nums[mid] + nums[right];
                    if (total > target) {
                        right--;
                    } else if (total < target) {
                        mid++;
                    } else {
                        list.add(Arrays.asList(nums[i], nums[left], nums[mid], nums[right]));
                        for (--right; right > mid && nums[right] == nums[right + 1]; right--) ;
                        for (++mid; mid < right && nums[mid] == nums[mid - 1]; mid++) ;
                    }
                }
            }

        }
        return list;
    }
}
