package com.jd.leetcode.Hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」 定义为：
 *
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 *
 *
 * 思路：遇到了要快速判断一个元素是否出现集合里的时候，就要考虑哈希法了。
 * 第一时间判断为，要么循环、要么最后得到1，所以使用set来存储每次得到的结果，如果是set已经存在的，那么说明是循环数字
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while(!set.contains(n) && n!=1){
            set.add(n);
            n = getResultNumber(n);
        }

        if(n == 1){
            return true;
        }else {
            return false;
        }
    }

    private int getResultNumber(int n) {
        int result = 0;

        while(n>0){
            int m = n%10;
            result += m*m;
            n = n/10;
        }
        return result;
    }
}
