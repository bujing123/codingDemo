package com.jd.leetcode.greedy;


import java.util.Arrays;

/**
 * 860. 柠檬水找零
 * <p>
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * <p>
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 * <p>
 * 注意，一开始你手头没有任何零钱。
 * <p>
 * 给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 */
public class LemonadeChange {

    /**
     * 这是无序手钱的办法
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int countFive = 0;
        int countTen = 0;
        int countTwenty = 0;
        for (int bill : bills) {
            if (bill == 5) {
                countFive++;
            } else if (bill == 10) {
                countTen++;
            } else {
                countTwenty++;
            }
        }
        //先把所有的十元票换到手
        countFive = countFive - countTen;
        //换完十元的票子后，没有多余的票子去换20的就说明失败了
        if (countFive < countTwenty) {
            return false;
        }
        //每张20元的票子需要拿15元去换，如果钱不够说明失败
        if (countFive * 5 + countTen * 10 < countTwenty * 15) {
            return false;
        }
        return true;
    }

    /**
     * 有序收钱的办法，只能按照排的队一个个收钱
     * 每收一张票子就计算现在手里的票子，如果票子不够了就说明失败
     */
    public boolean lemonadeChange2(int[] bills) {
        int countFive = 0;
        int countTen = 0;
        for (int bill : bills) {
            // 遇到是五块钱的顾客，直接收钱；是十块钱的顾客，直接透支五块钱给他；是二十块钱的顾客，看看有没有十块的，没有的话直接透支 5*3 给他；最后判断如果五块钱的钞票没了就说明失败
            if (bill == 5) {
                countFive++;
            } else if (bill == 10) {
                countFive--;
                countTen++;
            } else {
                if (countTen>0){
                    countTen--;
                    countFive--;
                }else {
                    countFive -= 3;
                }
            }
            if (countFive<0){
                return false;
            }
        }
        return true;
    }

}
