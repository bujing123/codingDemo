package com.jd.leetcode.backTrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原 IP 地址
 * <p>
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * <p>
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，
 * 但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * <p>
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，
 * 这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。
 * 你可以按 任何 顺序返回答案。
 * <p>
 * 思路：
 * 1、终止条件：截止位置到了最后，并且一共被分为了四段，三个小数点
 * 2、每一层循环逻辑：判断当前截取的数字是不是有效ip地址的一段；如果不是直接跳过；在递归前记录分段长度，递归后删除字符串长度
 */
public class RestoreIPAddress {
    static List<String> res = new ArrayList<>();

    public static List<String> restoreIpAddresses(String s) {
        if (s == null){
            return res;
        }

        backTrace(0, s, "", 0);
        return res;
    }

    static void backTrace(int start, String s, String str, int pointNum) {
        if (start == s.length() && pointNum == 4) {
            res.add(str);
            return;
        }
        if (start == s.length() || pointNum == 4){
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String substring = s.substring(start, i + 1);
            if (ifIPAddress(substring)) {
                if (str.length() == 0) {
                    str = substring;
                } else {
                    str = str + "." + substring;
                }
            } else {
                continue;
            }
            pointNum++;
            backTrace(i + 1, s, str, pointNum);
            pointNum--;
            if (str.length() == substring.length()){
                str = "";
            }else {
                str = str.substring(0,str.length()-substring.length()-1);
            }

        }

    }

    static boolean ifIPAddress(String ip) {
        if (ip.length()>3){
            return false;
        }
        if (ip.length() > 1 && ip.startsWith("0")) {
            return false;
        }
        if (Integer.valueOf(ip) > 255 || Integer.valueOf(ip) < 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        restoreIpAddresses("25525511135");
    }
}
