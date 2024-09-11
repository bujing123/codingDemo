package com.jd.leetcode.greedy;


import java.util.Arrays;
import java.util.LinkedList;

/**
 * 406. 根据身高重建队列
 * <p>
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
 * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * <p>
 * 请你重新构造并返回输入数组 people 所表示的队列。
 * 返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 * <p>
 * <p>
 * 贪心思路：局部最优：每次都把身高低的往里面插入，这样子就不影响已经在里面的数据（因为一个数据是身高，一个数据是比他高或者跟他一样高的人在前面的数量）
 * :把身高低的人往里面插，身高高的不会被影响数据
 * 先按照身高排序，然后依次插入，这样子就可以把身高低的按照他说的前面有几个身高比他高的数据来插入
 */
public class ReconstructQueue {


    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
        LinkedList<int[]> list = new LinkedList<>();
        for (int[] person : people) {
            list.add(person[1],person);
        }
        return list.toArray(new int[people.length][]);
    }
}
