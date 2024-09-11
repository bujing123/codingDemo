package com.jd.leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 496. 下一个更大元素 I
 * <p>
 * <p>
 * <p>
 * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
 * <p>
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
 * <p>
 * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
 * <p>
 * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
 */
public class NextGreaterElement {
    /**
     * 直接把nums2的对应的更高的值存到map中，然后遍历nums1，如果在map中存在，就拿出来
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        stack.push(nums2[0]);

        //拿到nums2的第一个比他大元素的map集合
        for (int i = 1; i < nums2.length; i++) {
            while (!stack.isEmpty()) {
                //更大的值，说明该值遇到的第一个更大值就是当前值
                if (nums2[i] > stack.peek()) {
                    Integer pop = stack.pop();
                    map.put(pop, nums2[i]);
                } else {
                    //遇到更小值了，直接退出while循环，找下一个值
                    break;
                }
            }
            //把当前值塞入栈中
            stack.push(nums2[i]);
        }

        int[] answer = new int[nums1.length];
        int index = 0;
        //遍历nums1，看是否在nums2中存在，存在就拿出来，不存在就说明没有 为-1
        for (int num : nums1) {
            if (map.containsKey(num)) {
                answer[index++] = map.get(num);
            } else {
                answer[index++] = -1;
            }
        }
        return answer;

    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add(null);
        list.add(null);
        System.out.println(list.size());
    }
}
