package com.jd.leetcode.StringQuestion;

/**
 *
 * 54. 替换数字（第八期模拟笔试）
 *
 * 给定一个字符串 s，它包含小写字母和数字字符，
 * 请编写一个函数，将字符串中的字母字符保持不变，而将每个数字字符替换为number。
 * 例如，对于输入字符串 "a1b2c3"，函数应该将其转换为 "anumberbnumbercnumber"。
 *
 * 思路：
 * 1、StringBuilder
 *
 * 2、直接在char数组中做改变，延长数组长度
 * 循环起点：使用双指针一个指向老长度的最后一个，一个指向新长度的最后一个；
 * 终点：两个指针指向同一个点，此时已经替换完毕
 */
public class ReplaceNumbers {
    public static String replaceNumber(String str){
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if(Character.isDigit(c)){
                sb.append("number");
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String replaceNumber2(String str){
        int oldLength = str.length();
        int newLength = 0;
        //先获取到新char数组的长度
        for (char c : str.toCharArray()) {
            if(Character.isDigit(c)){
                newLength += 5;
            }
            newLength++;
        }
        char[] ccc = new char[newLength];
        System.arraycopy(str.toCharArray(),0,ccc,0,oldLength);
        //拿到长度后就开始双指针循环放数字
        for(int left = oldLength-1, right = newLength-1;left<right; left--,right--){
            if(Character.isDigit(ccc[left])){
                ccc[right--] = 'r';
                ccc[right--] = 'e';
                ccc[right--] = 'b';
                ccc[right--] = 'm';
                ccc[right--] = 'u';
                ccc[right] = 'n';
            }else {
                ccc[right] = ccc[left];
            }
        }
        return new String(ccc);
    }

    public static void main(String[] args) {
        long l = System.nanoTime();
        System.out.println(replaceNumber2("dasds2ads12das1"));
        long l1 = System.nanoTime();
        System.out.println(replaceNumber("dasds2ads12das1"));
        long l2 = System.nanoTime();

        System.out.println(l1-l);
        System.out.println(l2-l1);
    }

}
