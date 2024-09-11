package com.jd.leetcode.StringQuestion;

/**
 *
 * 字符串的右旋转操作是把字符串尾部的若干个字符转移到字符串的前面。给定一个字符串 s 和一个正整数 k，请编写一个函数，将字符串中的后面 k 个字符移到字符串的前面，实现字符串的右旋转操作。
 *
 * 例如，对于输入字符串 "abcdefg" 和整数 2，函数应该将其转换为 "fgabcde"。
 *
 * 输入：输入共包含两行，第一行为一个正整数 k，代表右旋转的位数。第二行为字符串 s，代表需要旋转的字符串。
 *
 * 输出：输出共一行，为进行了右旋转操作后的字符串。
 *
 * 思路：
 * 第一种：
 * 使用StringBuilder先拼接右边需要旋转的，再去拼接左边的
 * 第二种：
 * 使用char数组，先整体倒序，然后根据给的整数来，区间倒序
 */
public class RightRotateString {
    public void rightRotate(String str, int number){
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        int length = str.length();
        for(int i = length-number; i<length; i++){
            sb.append(chars[i]);
        }
        for(int i = 0; i<length-number; i++){
            sb.append(chars[i]);
        }
        sb.toString();
    }

    public void rightRotate2(String str, int number){
        char[] c = str.toCharArray();
        reverse(c,0,c.length-1);
        reverse(c,0,number-1);
        reverse(c,number,c.length-1);
    }

    public void reverse(char[] c, int start, int end){
        while(start<end){
            char temp = c[end];
            c[end] = c[start];
            c[start] = temp;
            start++;
            end--;
        }
    }
}
