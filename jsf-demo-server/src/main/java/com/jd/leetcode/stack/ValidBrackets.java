package com.jd.leetcode.stack;


import java.util.Stack;

/**
 * 20. 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 * 因为要逐个匹配，所以很容易想到使用栈的先进后出特性
 * 比如  (({[  进栈后，  必须要先解决  ]再去解决 } 不然就是false
 *
 * 由此得知，按照对应的入栈后，遇到对应的，再出栈，最后为空就对了
 *
 *
 *   [][]]]   [)]  [[[]]
 */
public class ValidBrackets {


    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for(int i=0; i<chars.length; i++){
            char aChar = chars[i];

            if(aChar == '('){
                stack.push(')');
            }else if(aChar == '['){
                stack.push(']');
            }else if(aChar == '{'){
                stack.push('}');
            }else {
                if(stack.isEmpty()){
                    return false;
                }
                if (aChar == stack.peek()){
                    stack.pop();
                }else {
                    return false;
                }

            }
        }
        return stack.isEmpty();

    }

}
