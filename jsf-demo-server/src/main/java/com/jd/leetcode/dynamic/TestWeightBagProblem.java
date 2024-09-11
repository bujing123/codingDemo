package com.jd.leetcode.dynamic;


/**
 * 背包问题；
 */
public class TestWeightBagProblem {

    /**
     * 需要找到背包能放的最大价值的物品
     *
     * @param weight  每件物品的重量
     * @param value   每件物品的价值
     * @param bagSize 背包的最大容量
     */
    public static void testWeightBagProblem(int[] weight, int[] value, int bagSize) {
        int thingAmount = weight.length;
        // dp数组定义：dp[i][j]：代表有前i件物品，包裹重量为j时，最大容量
        int[][] dp = new int[thingAmount][bagSize + 1];

        // 公式递推：dp[i][j] ： 如果增加物品不往里面放新的东西，那么此时等于 dp[i-1][j]；
        // 如果增加 这一行新增的东西，那么此时等于 dp[i-1][j-weight[i]] + value[i]
        // 于是取两者的最大值即可
        // 因此初始化：i = 0的必须初始化出来；当重量超过第一件物品数量就加入
        for (int j = 0; j <= bagSize; j++) {
            if (j >= weight[0]) {
                dp[0][j] = value[0];
            }
        }
        //开始公式递推
        for (int i = 1; i < thingAmount; i++) {
            for (int j = 0; j <= bagSize; j++) {
                if (j - weight[i] >= 0) {
                    /**
                     * 当前背包的容量可以放下物品i
                     * 那么此时分两种情况：
                     *    1、不放物品i
                     *    2、放物品i
                     * 比较这两种情况下，哪种背包中物品的最大价值最大
                     */
                    dp[i][j] = Math.max(dp[i - 1][j], value[i] + dp[i - 1][j - weight[i]]);
                } else {
                    /**
                     * 当前背包的容量都没有当前物品i大的时候，是不放物品i的
                     * 那么前i-1个物品能放下的最大价值就是当前情况的最大价值
                     */
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
    }


    /**
     * 一个物品只能用一次的 01 背包
     * 一维数组取代二维数组，节约空间
     *
     * @param weight
     * @param value
     * @param bagSize
     */
    public static void testWeightBagProblem2(int[] weight, int[] value, int bagSize) {
        int[] dp = new int[bagSize + 1];
        for (int i = 0; i < weight.length; i++) {
            for (int j = bagSize; j > weight[i]; j--) {
                dp[j] = Math.max(dp[j], value[i] + dp[j - weight[i]]);
            }
        }

    }


    /**
     * 一个物品可以用多次的完全背包
     * <p>
     * 思路：每个物品可以用多次，那么就从小到大排序，如此在后面重量变大的时候，就可以重复放物品
     */
    public static void testWeightBagProblem3(int[] weight, int[] value, int bagSize) {
        int[] dp = new int[bagSize + 1];
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j <= bagSize; j++) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
    }


    /**
     * 一个物品可以固定次数的完全背包
     * <p>
     * 思路：每个物品可以用多次，那么就从小到大排序，如此在后面重量变大的时候，就可以重复放物品
     */
    public static void testWeightBagProblem3(int[] weight, int[] value, int[] nums, int bagSize) {
        int[] dp = new int[bagSize + 1];
        for (int i = 0; i < weight.length; i++) {
            for (int j = bagSize; j >= weight[i]; j--) {
                for (int k = 1; j <= nums[i]; j++) {
                    if (j - k * weight[i] < 0) {
                        break;
                    }
                    dp[j] = Math.max(dp[j], dp[j - k * weight[i]] + k * value[i]);
                }
            }
        }
    }
}
