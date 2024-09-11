package com.jd.leetcode.greedy;


import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * 1005. K 次取反后最大化的数组和
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 * <p>
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * <p>
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 * <p>
 * 思路：
 * 局部最优：让大的数字尽量全变为正数  整体最优：值就最大
 */
public class LargestSumAfterKNegations {

    public int largestSumAfterKNegations(int[] nums, int k) {

        nums = IntStream.of(nums)
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue)
                .toArray();
        //把所有的大负数反过来
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
        }
        //全部反过来后，如果k还是大于0，就把最后一个数字来回变化
        int i = k % 2;
        if (i == 1) {
            //说明k为奇数，最后一个数字改为负数
            nums[nums.length - 1] = -nums[nums.length - 1];
        }

        return Arrays.stream(nums).sum();

    }
}
