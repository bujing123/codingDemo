package com.jd.leetcode.greedy;


import java.util.Arrays;

/**
 * 135. 分发糖果
 * <p>
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * <p>
 * 你需要按照以下要求，给这些孩子分发糖果：
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 * <p>
 * <p>
 * 思路：
 * 贪心：局部最优：先从左到右给糖，如果当前孩子比他左边的孩子分高，那就比左边孩子的糖多一颗；然后从右到左给糖，如果当前孩子比右边孩子分高，就比右边孩子的糖多一颗；
 * 之所以这样子是因为，右边孩子比左边孩子分高的时候，获得的糖数量需要比左边孩子多一颗，而他左边孩子的糖数量又取决于左边孩子的左边孩子；
 * 同理，左边孩子比右边孩子分高的时候，获得的糖数量需要比右边孩子多一颗，而右边孩子的糖数量又取决于她右边的孩子；因此一个从左到右给糖；一个从右到左给糖
 *
 * 贪心就贪在：一开始除非你比左边的人分高，我才给你糖；第二次除非你比右边的人分高，而且你糖还没他多，我才给你
 */
public class Candy {

    public int candy(int[] ratings) {
        if (ratings.length < 2) return ratings.length;

        int[] result = new int[ratings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = 1;
        }
        //从左到右给孩子糖
        for (int i = 1; i < result.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                result[i] = result[i - 1] + 1;
            }
        }
        //从右向左给孩子糖
        for (int i = result.length - 2; i >= 0; i--) {
            if ((ratings[i] > ratings[i + 1]) && result[i] <= result[i+1]) {
                result[i] = result[i + 1] + 1;
            }
        }
        return Arrays.stream(result).sum();
    }

}
