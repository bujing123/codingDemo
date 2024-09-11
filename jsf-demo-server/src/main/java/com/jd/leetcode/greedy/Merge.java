package com.jd.leetcode.greedy;


import java.util.Arrays;
import java.util.LinkedList;

/**
 * 56. 合并区间
 * <p>
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * <p>
 * <p>
 * 贪心思路：按照起始位置排序后，如果当前节点在范围内，那么合并为一个；
 */
public class Merge {

    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2) return intervals;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        LinkedList<int[]> list = new LinkedList<>();

        //作为边界范围的数组
        int[] ele = new int[2];
        ele[0] = intervals[0][0];
        ele[1] = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= ele[1]) {
                ele[1] = Math.max(ele[1], intervals[i][1]);
            } else {
                list.add(new int[]{ele[0], ele[1]});
                ele[0] = intervals[i][0];
                ele[1] = intervals[i][1];
            }
            if (i == intervals.length - 1) {
                list.add(ele);
            }
        }
        return list.toArray(new int[list.size()][]);
    }

}
