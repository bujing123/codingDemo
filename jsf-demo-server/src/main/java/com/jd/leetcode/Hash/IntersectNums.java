package com.jd.leetcode.Hash;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 349. 两个数组的交集
 * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。
 * 输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
 * <p>
 * <p>
 * 出参不能有重复的
 * 思路总结：
 * 数组的交集
 * -->先将一个数组的数都放到一个set里面
 * --->遍历第二个数组，将set里面包含的数字放到第二个set里面
 * ---->第二个set存的就是共同数字，直接转化为数组，出参
 */
public class IntersectNums {
    public int[] intersection(int[] nums1, int[] nums2) {
        //数组下标存实际数据，value存出现次数
        int[] hash1 = new int[1002];
        for (int i : nums1) {
            hash1[i]++;
        }
        int[] hash2 = new int[1002];
        for (int i : nums2) {
            hash2[i]++;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < hash1.length; i++) {
            if (hash1[i] != 0) {
                if (hash2[i] != 0) {
                    list.add(i);
                }
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for (Integer integer : list) {
            res[i++] = integer;
        }
        return res;
    }

    /**
     * 通过set来
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i : nums1) {
            hashSet.add(i);
        }
        HashSet<Integer> hashSet1 = new HashSet<>();

        for (int i : nums2) {
            if (hashSet.contains(i)) {
                hashSet1.add(i);
            }
        }
        int[] res = new int[hashSet1.size()];
        int i = 0;
        for (Integer integer : hashSet1) {
            res[i++] = integer;
        }
        return res;
    }


    public int[] intersection3(int[] nums1, int[] nums2) {
        int[] hashSet1 = new int[1002];
        int[] hashSet2 = new int[1002];
        for (int i = 0; i < nums1.length; i++) {
            hashSet1[nums1[i]]++;
        }
        for (int i = 0; i < nums2.length; i++) {
            hashSet2[nums2[i]]++;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 1002; i++) {
            if(hashSet1[i] != 0 && hashSet2[i] != 0){
                set.add(i);
            }
        }
        int[] result = new int[set.size()];
        int index = 0;
        for (Integer integer : set) {
            result[index++] = integer;
        }
        return result;
    }
}
