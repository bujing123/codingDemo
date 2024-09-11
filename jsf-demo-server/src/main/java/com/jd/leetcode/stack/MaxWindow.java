package com.jd.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 239. 滑动窗口最大值
 * <p>
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回 滑动窗口中的最大值 。
 * <p>
 * <p>
 * 思路：
 * 1、先定义一个 比较合适的队列，这个队列，每次滑动窗口移动的时候，减去左边的值，存入右边的值；这样子每次滑动完直接取队列中最大值即可
 * 2、想要每次取队列中最大值这个办法，就是一个比较难的点：
 * 可以这样，每次滑动窗口向右移动时，新增的值如果比里面值大，就把大于里面的都删了，保持一个滑动窗口是递减数组；
 * 然后删数字的时候，轮到数字相同的时候再删，这样就可以保证，队列顶的值就是最大值
 */
public class MaxWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        MyQue myQue = new MyQue();
        for (int i = 0; i < k; i++) {
            myQue.offer(nums[i]);
        }
        int[] result = new int[nums.length - k + 1];
        int index = 0;
        result[index++] = myQue.peek();
        for (int i = k; i < nums.length; i++) {
            myQue.poll(nums[i - k]);
            myQue.offer(nums[i]);
            result[index++] = myQue.peek();
        }

        return result;
    }


    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();

        deque.offerLast(11);
        deque.offerLast(22);
        deque.offerLast(33);
        deque.offerLast(44);

        deque.offer(55);
        deque.offer(66);

        deque.offerFirst(77);
        deque.offerFirst(88);

        System.out.println(deque);
        System.out.println(deque.peek());
        System.out.println(deque.peekLast());
        System.out.println(deque.peekFirst());
        System.out.println(deque.poll());
        System.out.println(deque.pollLast());
        System.out.println(deque.pollFirst());

    }
}

class MyQue {

    /**
     * Deque 的 peek和peekFirst一样；poll和pollFirst一样；offer和offerLast一致；Deque默认是先进先出的双端队列
     */
    Deque<Integer> deque;

    public MyQue() {
        deque = new LinkedList<>();
    }

    void offer(Integer i) {
        while (!deque.isEmpty() && deque.peekLast() < i) {
            deque.pollLast();
        }
        deque.offer(i);
    }

    void poll(Integer i) {
        if (!deque.isEmpty() && deque.peek().equals(i)) {
            deque.poll();
        }
    }

    Integer peek() {
        return deque.peek();
    }
}