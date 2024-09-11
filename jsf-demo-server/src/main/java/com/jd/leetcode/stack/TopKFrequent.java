package com.jd.leetcode.stack;


import com.jd.fastjson.JSON;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <p>
 * <p>
 * 思路：
 * 先搞一个map，key存值，value存次数；
 * 根据map搞一个优先队列，小顶堆的，每次把出现次数最低的顶点弹出
 * 最后堆里面有哪些数据，就是前 k 个出现次数的
 * <p>
 * Comparator接口说明:
 * 返回负数，形参中第一个参数排在前面；返回正数，形参中第二个参数排在前面
 */
public class TopKFrequent {
    public static int[] topKFrequent(int[] nums, int k) {
        //存放次数的map
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> deque = new PriorityQueue<>((pair1, pair2) -> pair1[1] - pair2[1]);
        //每次将小的弹出，大的存进去
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (deque.size() < k) {
                deque.offer(new int[]{entry.getKey(), entry.getValue()});
            } else {
                if (entry.getValue() > deque.peek()[1]) {
                    deque.poll();
                    deque.offer(new int[]{entry.getKey(), entry.getValue()});
                }
            }
        }
        int[] result = new int[k];
        int index = 0;
        //此时就是出现次数多的 k个
        for (int[] ints : deque) {
            result[index++] = ints[0];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 4, 5, 6, 6, 4, 3, 7, 9, 3, 4, 7, 1, 3};
        int k = 3;
        System.out.println(JSON.toJSONString(topKFrequent(nums, k)));
    }


    public static int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> deque = new PriorityQueue<>((pair1, pair2) -> pair1[1] - pair2[1]);
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (deque.size()<k){
                deque.offer(new int[]{entry.getKey(),entry.getValue()});
            }else {
                if (entry.getValue()>deque.peek()[1]){
                    deque.poll();
                    deque.offer(new int[]{entry.getKey(),entry.getValue()});
                }
            }
        }
        int[] res = new int[k];
        int index = 0;
        for (int[] ints : deque) {
            res[index++] = ints[0];
        }

        return res;
    }

}
