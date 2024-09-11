package com.jd.leetcode.StringQuestion;

/**
 * 28. 找出字符串中第一个匹配项的下标
 * 给你两个字符串 haystack 和 needle ，
 * 请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果 needle 不是 haystack 的一部分，则返回  -1 。
 * <p>
 * 思路：
 * KMP算法:
 * 先得到next数组，next数组里面存储的前缀后缀匹配的长度；
 * 循环匹配的时候，如果发现不匹配了，根据前缀表的数值去找重新匹配的位置
 * <p>
 * 先拿到前缀表 两个指针，相同处理指针一起+，不同处理，前指针一直回溯
 * 拿到前缀表后，两个指针，一个指着文本字符串，一个指着模式字符串，遇到相同，指针加，遇到不同，根据前缀表回溯指针，最后如果相等的话直接返回下标
 */
public class IndexOf {
    public int strStr(String haystack, String needle) {
        if (needle == null) {
            return 0;
        }
        int[] next = getNext(needle);

        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && needle.charAt(j) != haystack.charAt(i)) {
                j = next[j - 1];
            }
            if (needle.charAt(j) == haystack.charAt(i)) {
                j++;
            }
            if (j == needle.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 计算模式串的匹配值next数组
     *
     * @param needle
     * @return
     */
    private int[] getNext(String needle) {
        int[] next = new int[needle.length()];
        int j = 0;  //j代表前缀后缀匹配的值大小
        next[0] = 0;
        for (int i = 1; i < needle.length(); i++) {
            while (j > 0 && needle.charAt(j) != needle.charAt(i)) {
                j = next[j - 1];
            }
            if (needle.charAt(j) == needle.charAt(i)) {
                //两个指着的地方相同
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    private int[] getNext2(String needle){
        int[] next = new int[needle.length()];
        next[0] = 0;
        int j =0;
        for (int i=1; i<needle.length(); i++){
            while (j>0 && needle.charAt(j) != needle.charAt(i)){
                j = next[j-1];
            }
            if (needle.charAt(j) == needle.charAt(i)){
                j++;
            }
            next[i] = j;
        }

        return next;
    }

    public int strStr2(String haystack, String needle){
        int[] next2 = getNext2(needle);
        int j=0;
        for (int i=0; i<haystack.length(); i++){
            while (j>0 && haystack.charAt(i) != needle.charAt(j)){
                j = next2[j-1];
            }
            if (haystack.charAt(i) == needle.charAt(j)){
                j++;
            }
            if (j == needle.length()){
                return i-j+1;
            }
        }
        return -1;
    }

    //abc abc abd
    // abc abd



}
