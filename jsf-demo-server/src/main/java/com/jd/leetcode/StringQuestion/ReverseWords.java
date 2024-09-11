package com.jd.leetcode.StringQuestion;

/**
 * 151. 反转字符串中的单词
 * 翻转字符串里的单词
 *
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 *
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 *
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 *
 * 注意：输入字符串 s 中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 *
 * 思路：
 * 分三步
 * 1、先将多余空格删除
 * 2、将整个字符串反转
 * 3、将每个空格间的单词进行反转
 */
public class ReverseWords {
    public String reverseWords(String s) {
        //先删除多余空格
        StringBuilder sb = removeSpace(s);
        //将整个字符串反转
        reverseSB(sb);
        //最后将每个空格隔开的小字符串进行反转
        reverseInWords(sb);
        return sb.toString();
    }

    private void reverseInWords(StringBuilder sb) {
        int start = 0;
        while(start<sb.length()){
            int end = start;
            while(end<sb.length() && sb.charAt(end) != ' '){
                end++;
            }
            reverseSBWords(sb,start,end);
            start = end+1;
        }

    }

    private void reverseSBWords(StringBuilder sb, int start, int end) {
        end--;
        while(start<end){
            char temp = sb.charAt(start);
            sb.setCharAt(start,sb.charAt(end));
            sb.setCharAt(end,temp);
            start++;
            end--;
        }
    }

    private void reverseSB(StringBuilder sb) {
        int start = 0;
        int end = sb.length()-1;

        while(start<end){
            char temp = sb.charAt(start);
            sb.setCharAt(start,sb.charAt(end));
            sb.setCharAt(end,temp);
            start++;
            end--;
        }
    }

    private StringBuilder removeSpace(String s) {
        //从头开始 一个一个循环，不是空格则加入，是空格的话，如果前一个不是空格，也加入
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = s.length()-1;
        while(s.charAt(start) ==  ' '){
            start++;
        }
        while(s.charAt(end) == ' '){
            end--;
        }
        while(start <= end){
            char c = s.charAt(start);
            if(c != ' ' || sb.charAt(sb.length()-1) != ' '){
                sb.append(c);
            }
            start++;
        }
        return sb;
    }
}
