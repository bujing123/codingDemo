package com.jd.leetcode.greedy;


import java.util.Arrays;

/**
 * 455. 分发饼干
 * <p>
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * <p>
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；
 * 并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，
 * 我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
 * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 * <p>
 * <p>
 * 思路：
 * 遍历饼干，如果有可以满足的小人儿，就满足小人儿，然后去看下一个小人儿；
 */
public class FindContentChildren {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int start = 0;
        for (int i : g) {
            System.out.println(start);
            for (int j = start; j < s.length; j++) {
                if (s[j]>=i){
                    count++;
                    start = j+1;
                    break;
                }
            }
        }

        return count;
    }

    public int findContentChildren2(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int start = 0;
        for(int j = 0; j<s.length && start<g.length; j++){
            if (s[j]>=g[start]){
                count++;
                start++;
            }
        }

        return count;
    }
}
