package com.jd.leetcode.greedy;

/**
 * 134. 加油站
 * <p>
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * <p>
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * <p>
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 * <p>
 * <p>
 * <p>
 * 贪心思路：
 * 局部最优，如果差值的总和是>0的，那从左开始到右挨个找区间，肯定会找到小于0的区间，找到小于0的区间就说明另一个区间一定大于0；那么从后面的区间继续找区间最大值即可
 */
public class CanCompleteCircuit {


    public int canCompleteCircuit(int[] gas, int[] cost) {
        //定义区间油量
        int sumGas = 0;
        int totalGas = 0;
        //定义合适的起点，一开始默认为零点
        int index = 0;
        for (int i = 0; i < gas.length; i++) {
            sumGas = sumGas + gas[i] - cost[i];
            totalGas = totalGas + gas[i] - cost[i];
            if (sumGas < 0) {
                index = i + 1;
                sumGas = 0;
            }
        }
        if (totalGas<0) return -1;
        return index;

    }

}
