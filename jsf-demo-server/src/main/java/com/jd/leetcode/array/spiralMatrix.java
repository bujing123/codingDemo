package com.jd.leetcode.array;

/**
 * 59. 螺旋矩阵 II
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * <p>
 * 思路：
 * 每条边左闭右开
 * 考虑左节点，不考虑右节点
 */
public class spiralMatrix {
    public static int[][] generateMatrix(int n) {
        //定义每条边遍历开始的x轴和y轴起始点
        int startX = 0, startY = 0;
        //每跑一个圈，都会往里面缩一格，因此每次一圈要减1；又因为一开始每一条边都不遍历最后一个节点，所以一开始就定义为1
        int offest = 1;
        //跑圈一直要跑完n的平方那么多数字，所以以这个作为循环结束条件；但是以这个作为条件的话，循环条件不好写，index需要单独++；
        //因此以每跑一圈都会少两行来算，一共有n行，n/2>0则说明
        int[][] nums = new int[n][n];
        int loop = n / 2; //除数为循环次数
        int residue = n % 2;//余数为1说明恰好剩一个
        int i,j;//循环数
        int m = 1;//填充数字
        while (loop-- > 0) {
            for(j = startX; j<n-offest; j++){
                nums[startY][j] = m++;
            }
            for (i = startY; i<n-offest; i++){
                nums[i][j] = m++;
            }
            for (j = j; j>startX; j--){
                nums[i][j] = m++;
            }
            for (i = i; i>startY; i--){
                nums[i][j] = m++;
            }
            startX++;startY++;
            offest++;
        }


        if (residue == 1) {
            nums[startX][startY] = n * n;
        }

        return nums;
    }

    public static void main(String[] args) {
        generateMatrix(3);
    }
}
