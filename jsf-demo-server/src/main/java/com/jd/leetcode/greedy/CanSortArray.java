package com.jd.leetcode.greedy;

public class CanSortArray {

    /**
     局部最优推出全局最优；
     每一个相同的分区空间最大值，小于第二个空间的值就是false；
     那么就循环分区，获取每个分区的最大值和下一个分区的第一个值进行比较即可；
     至于怎么分区，就靠二进制位数为1的数目是否相同
     */
    public boolean canSortArray(int[] nums) {
        int nowMax = 0;//此时分区中最大值
        int lastMax = 0;//上一个分区中最大值
        int lastCount = 0;//上一个数的为1的数目
        for(int num:nums){
            int count = Integer.bitCount(num);
            if(lastCount == count){
                //两个挨着，说明可以调整排序，绝对有序，更新当前最大值
                nowMax = Math.max(num, nowMax);
            }else{
                //此时说明不挨着了，要分区了，需要更新上区和当前区的各个值
                lastCount = count;
                lastMax = nowMax;
                nowMax = num;
            }
            //每次判断上区最大值和当前值的大小
            if(lastMax>num){
                return false;
            }
        }
        return true;
    }
}
