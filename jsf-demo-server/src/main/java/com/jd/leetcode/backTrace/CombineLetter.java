package com.jd.leetcode.backTrace;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 思路：
 * 终止条件：符合要求了，字母字符串长度等于给定数字字符串长度
 * 每层循环逻辑：从剩下的字符串之中对应的字母里面循环拿到需要的字符
 */
public class CombineLetter {
    static List<String> res = new ArrayList<>();
    static Map<Integer,String> map = new HashMap<>();
    static {
        map.put(2,"abc");
        map.put(3,"def");
        map.put(4,"ghi");
        map.put(5,"jkl");
        map.put(6,"mno");
        map.put(7,"pqrs");
        map.put(8,"tuv");
        map.put(9,"wxyz");
    }


    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0){
            return res;
        }
        backTrace("",digits,0);

        return res;

    }


    static void backTrace(String abc, String digits, int left){
        if (abc.length() == digits.length()){
            res.add(abc);
            return;
        }
        int value = Character.getNumericValue(digits.charAt(left));
        if (!map.containsKey(value)){
            return;
        }
        for (char c : map.get(value).toCharArray()) {
            String newABC = abc + c;
            backTrace(newABC,digits,left+1);
        }
    }

    public static void main(String[] args) {
        letterCombinations("23");
    }

}
