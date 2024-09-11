package com.jd.leetcode.greedy;


/**
 * 738. 单调递增的数字
 * <p>
 * 当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。
 * <p>
 * 给定一个整数 n ，返回 小于或等于 n 的最大数字，且数字呈 单调递增 。
 * <p>
 * 找几个数字：
 * 2384 -》 2379   1021-》 0999
 * 贪心思路：
 * 局部最优，从后向前排序时，遇到比他大的数字，就把后面所有数字变为9，当前数字-1
 */
public class MonotoneIncreasingDigits {

    public static int monotoneIncreasingDigits(int n) {
        String number = String.valueOf(n);
        char[] chars = number.toCharArray();
        for (int i = number.length() - 2; i >= 0; i--) {
            if (chars[i] > chars[i+1]) {
                chars[i] = (char) (Integer.valueOf(chars[i])-1);
                for (int j = i+1; j<chars.length && chars[j] != '9'; j++){
                    chars[j] = '9';
                }
            }
        }
        return Integer.parseInt(new String(chars));
    }

    public static void main(String[] args) {
        monotoneIncreasingDigits(10);
    }
}
