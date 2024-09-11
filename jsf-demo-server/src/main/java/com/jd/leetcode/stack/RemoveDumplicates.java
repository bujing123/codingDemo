package com.jd.leetcode.stack;

import java.util.Stack;

/**
 * 1047. 删除字符串中的所有相邻重复项
 * <p>
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * <p>
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * <p>
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 */
public class RemoveDumplicates {

    public static String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(stack.isEmpty()){
                //为空
                stack.push(c);
            }else {
                //不为空
                if(stack.peek().equals(c)){
                    stack.pop();
                }else {
                    stack.push(c);
                }
            }

        }
        String str = "";
        while(!stack.isEmpty()){
            str = stack.pop() + str;
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates("abbaca"));
    }
}
