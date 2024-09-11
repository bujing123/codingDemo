package com.jd.leetcode.backTrace;

/**
 * 37. 解数独
 * <p>
 * 编写一个程序，通过填充空格来解决数独问题。
 * <p>
 * 数独的解法需 遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * <p>
 * 思路：
 * 终止条件：无，就是一直往下找到最后一个数字
 * 每一层循环逻辑：先两层for循环找到需要补数字的位置，然后循环1到9；如果有可以补的数字再往下循环
 */
public class SolveSudoku {

    public void solveSudoku(char[][] board) {
        bakTrace(board);
    }


    /**
     * 补完任意一种情况后，直接返回true；
     */
    boolean bakTrace(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                for (char a = '1'; a <= '9'; a++) {
                    if (isValid(i, j, a, board)) {
                        board[i][j] = a;
                        if (bakTrace(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }

    boolean isValid(int i, int j, char value, char[][] board) {
        //同一行不能有
        for (int m = 0; m < board.length; m++) {
            if (board[i][m] == value) {
                return false;
            }
        }
        //同一列不能有
        for (int m = 0; m < board.length; m++) {
            if (board[m][j] == value) {
                return false;
            }
        }
        //同一个 3x3 小框内也不能用
        int startX = (i / 3) * 3;
        int startY = (j / 3) * 3;
        for (int m = startX; m < startX + 3; m++) {
            for (int n = startY; n < startY + 3; n++) {
                if (board[m][n] == value) {
                    return false;
                }
            }
        }
        return true;
    }

}
