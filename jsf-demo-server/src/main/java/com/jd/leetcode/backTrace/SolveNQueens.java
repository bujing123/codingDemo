package com.jd.leetcode.backTrace;


import java.util.ArrayList;
import java.util.List;

/**
 * 51. N 皇后
 * <p>
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * <p>
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * <p>
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 思路：
 * 第一种使用boolean数组
 * 终止条件：放置完了四个皇后，则说明此时是一种解；
 * 每次递归的行数+1；然后for循环代表每一行中不同的列数，一一判断是否符合，把符合的打成true；最后根据数组的true false来生成list数组，这下面是每次递归都生成每一行的数据
 *
 * 第二种：直接使用char数组，char[][]，可以放皇后的就进去把 .改成Q 然后符合的时候，再写一个方法把char数组改成list；
 *
 * 判断条件：
 * 按照如下标准去重：
 * 不能同行
 * 不能同列
 * 不能同斜线 （45度和135度角）
 */
public class SolveNQueens {
    List<List<String>> res = new ArrayList<>();
    List<String> list = new ArrayList<>();

    /**
     * 定义
     */
    boolean[][] canUse;

    public List<List<String>> solveNQueens(int n) {
        canUse = new boolean[n][n];
        backTrace(n,0);
        return res;
    }

    /**
     * 需要遍历完所有情况，因此不需要返回值
     * n:n皇后的n
     * startI：从第几行开始
     */
    void backTrace(int n, int startI) {
        if (list.size() == n) {
            res.add(new ArrayList<>(list));
            return;
        }
        //挨个遍历本行的每一个点位，找到可以放置皇后的点位
        for (int j = 0; j < n; j++) {
            if (ifPlace(startI, j)) {
                //可以放置
                String sb = getStringBuilder(j, n);
                list.add(sb);
                setCanUse(startI,j,true);
                backTrace(n, startI + 1);
                setCanUse(startI,j,false);
                list.remove(list.size() - 1);
            }
        }

    }

    private static String getStringBuilder(int j, int n) {
        //先放置j个.  然后放置Q 然后放置n-j-1个.
        StringBuilder sb = new StringBuilder();
        int count = n - j - 1;
        while (j-- > 0) {
            sb.append(".");
        }
        sb.append("Q");
        while (count-- > 0) {
            sb.append(".");
        }
        return sb.toString();
    }

    /**
     * 给一个坐标和需要设置的值；把横线竖线和斜线都改成需要设置的值
     * i:行 j:列
     * 每次把皇后位置设置为true
     */
    private void setCanUse(int i, int j, boolean value) {
        canUse[i][j] = value;
    }


    /**
     * 判断该点位是否可以放置皇后
     * 在横线竖线和斜线上找皇后，找到皇后就返回false
     */
    boolean ifPlace(int i, int j) {
        //同行其实可以不查，因为每次进入这个查询都是一行之中唯一一个
        for (int m = 0; m < canUse.length; m++) {
            if (canUse[i][m]){
                return false;
            }
        }
        for (int n = 0; n < canUse.length; n++) {
            if(canUse[n][j]){
                return false;
            }
        }
        for (int m = i, n = j; m >= 0 && n >= 0; m--, n--) {
            if (canUse[m][n]){
                return false;
            }
        }
        for (int m = i, n = j; m < canUse.length && n < canUse.length; m++, n++) {
            if (canUse[m][n]){
                return false;
            }
        }
        for (int m = i, n = j; m < canUse.length && n >= 0; m++, n--) {
            if (canUse[m][n]){
                return false;
            }
        }
        for (int m = i, n = j; m >= 0 && n <canUse.length; m--, n++) {
            if (canUse[m][n]){
                return false;
            }
        }
        return true;
    }

}
