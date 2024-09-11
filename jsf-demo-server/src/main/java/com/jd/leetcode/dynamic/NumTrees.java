package com.jd.leetcode.dynamic;


import com.jd.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 96. 不同的二叉搜索树
 * <p>
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
 * 返回满足题意的二叉搜索树的种数。
 * <p>
 * <p>
 * dp数组定义：dp[n]代表n个节点生成二叉搜索树的个数
 * 公式：dp[n]  =  假设n=10；那么以 1为头结点的数据=左子树0个节点，右子树9个节点；5为头结点=左子树四个，右子树五个
 * 初始化：dp[0] = 0; dp[1] = 1  dp[2] = 2
 */
public class NumTrees {

    public int numTrees(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            //从1开始，代表头节点为1时的个数
            for (int j=1; j<=i; j++){
                //左边节点个数为  j-1个，右边就是 i-j个
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }


}
