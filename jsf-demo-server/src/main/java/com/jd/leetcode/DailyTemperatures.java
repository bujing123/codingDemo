package com.jd.leetcode;


import java.util.Stack;

/**
 * 739. 每日温度
 * <p>
 * <p>
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * <p>
 * 思路：
 * 使用一个栈来存放温度的下标，然后每次遇到更大的温度就把里面弹出，把大的存进去，同时给下标处赋值，下标的差距值
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < temperatures.length; i++) {
            //循环比较，把小于当前值的都踢出来
            while (!stack.isEmpty()) {
                //比较此前存入的温度和当前温度
                if (temperatures[stack.peek()] < temperatures[i]) {
                    //说明此时遇到更大值了，可以观测到更高温度了，推出此前更小的值
                    Integer pop = stack.pop();
                    answer[pop] = i - pop;
                } else {
                    //当前温度更小，说明已经不能观测了，直接退出while
                    break;
                }
            }
            //比较完事后，把当前值塞进去
            stack.push(i);
        }
        return answer;
    }


}
