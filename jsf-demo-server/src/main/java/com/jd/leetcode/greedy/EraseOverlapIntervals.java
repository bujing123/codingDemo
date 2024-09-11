package com.jd.leetcode.greedy;


import java.util.Arrays;

/**
 * 435. 无重叠区间
 * <p>
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi]。
 * 返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 * <p>
 * <p>
 * 贪心思路：
 * 局部最优：按照起始点排序后；从左到右，首先第一个气球范围内的，只要沾边都要打爆；然后如果没有气球范围内的后，就开始去判断下一个不在范围内的气球
 */
public class EraseOverlapIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length < 2) return 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int endIndex = intervals[0][1];
        int delete = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < endIndex) {
                delete++;
                endIndex = Math.min(endIndex, intervals[i][1]);
            }else {
                endIndex = intervals[i][1];
            }
        }
        return delete;
    }

}
