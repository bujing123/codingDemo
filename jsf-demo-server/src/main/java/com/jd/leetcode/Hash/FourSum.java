package com.jd.leetcode.Hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 454. 四数相加 II
 *
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 *
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 *
 *
 * 思路：
 * 遍历 1 2数组，将和放到map的key中，value存放每个和出现的次数；
 * 3 4数组 做相同操作
 * 然后遍历第一个map，使用0-，来查看另一个map中是否包含需要的数字，然后再将出现的次数相乘，则说明这是多少次的元组
 */
public class FourSum {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {

        Map<Integer,Integer> map12 = new HashMap<>();
        Map<Integer,Integer> map34 = new HashMap<>();
        int sum = 0;
        int n = nums1.length;
        for(int i = 0; i< n; i++){
            for(int j = 0; j<n; j++){
                int sum1 = nums1[i] + nums2[j];
                if (map12.containsKey(sum1)){
                    map12.put(sum1,map12.get(sum1)+1);
                }else {
                    map12.put(sum1,1);
                }

                int sum2 = nums3[i] + nums4[j];
                if (map34.containsKey(sum2)){
                    map34.put(sum2,map34.get(sum2)+1);
                }else {
                    map34.put(sum2,1);
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : map12.entrySet()) {
            Integer key = entry.getKey();
            if(map34.containsKey(-key)){
                sum += entry.getValue() * map34.get(-key);
            }
        }

        return sum;


    }

    public int fourSumCount2(int[] nums1, int[] nums2, int[] nums3, int[] nums4){
        Map<Integer,Integer> map12 = new HashMap<>();
        int sum = 0;
        int n = nums1.length;
        for(int i = 0; i< n; i++){
            for(int j = 0; j<n; j++){
                int sum1 = nums1[i] + nums2[j];
                map12.put(sum1,map12.getOrDefault(sum1,0)+1);
            }
        }

        for(int i=0;i<n;i++){
            for(int j = 0; j<n; j++){
                int sum2 = nums3[i]+nums4[j];
                if(map12.containsKey(-sum2)){
                    sum += map12.get(-sum2);
                }
            }
        }

        return sum;
    }
}
