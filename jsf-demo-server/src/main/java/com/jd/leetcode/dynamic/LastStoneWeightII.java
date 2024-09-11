package com.jd.leetcode.dynamic;


/**
 * 1049. 最后一块石头的重量 II
 * <p>
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 * <p>
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 * <p>
 * <p>
 * <p>
 * 思路：其实就是把石头分为两堆；
 * 相当于从 包裹重量为 total/2 的背包里面装最大重量；然后以剩下的石头重量减去这一堆石头；就是差值最低了（满足一堆石头最大为 total/2 时，差值为0）
 * <p>
 * 理解为：把石头分为两堆，互相粉碎，最后剩下的最小，那么就是两堆石头重量最接近；最接近时就是一堆一半重量。那么就在一半重量的背包中找最大重量
 *
 *
 * 动态规划思路：
 * 定义：dp[j]:最大重量为j的背包能存放多少重量
 * 公式：dp[j] 要么就不放新的一层i的石头；要么就放了以后找 最大重量为 j-stone[i]重量的背包能存放多少重量，取最大值
 * 初始化：无需初始化，因为是倒序，如果初始化的话，把i=0的第一行初始化出来
 *
 */
public class LastStoneWeightII {

    public int lastStoneWeightII(int[] stones) {
        int total = 0;
        for (int stone : stones) {
            total += stone;
        }
        int target = total / 2;
        int[] dp = new int[target + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], stones[i] + dp[j - stones[i]]);
            }

            //剪枝，此时已经达到了最大值
            if (dp[target] == target){
                break;
            }
        }
        return total-dp[target]*2;

    }
}
