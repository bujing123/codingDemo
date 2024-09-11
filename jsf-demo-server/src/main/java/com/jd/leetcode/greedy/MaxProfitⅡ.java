package com.jd.leetcode.greedy;


/**
 * 714. 买卖股票的最佳时机含手续费
 * <p>
 * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * <p>
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * <p>
 * 返回获得利润的最大值。
 * <p>
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * <p>
 * <p>
 * 贪心思路：尽量低买高卖
 *
 * 贪心原则就是，尽量低买高卖，所以每次比当前成本价格就高就立马卖出；然后成本变成卖出价格，这样子后面如果有更高价格，也可以继续卖；遇到更低的成本才去买入
 */
public class MaxProfitⅡ {

    public int maxProfit(int[] prices, int fee) {
        int max = 0;
        int buy = prices[0] + fee;
        for (int price : prices) {
            if (price > buy) {
                //遇到更高价格，卖出
                max += price - buy;
                buy = price;
            } else if (price + fee < buy) {
                //遇到成本更低的了，在这里买入
                buy = price + fee;
            }
        }
        return max;
    }
}
