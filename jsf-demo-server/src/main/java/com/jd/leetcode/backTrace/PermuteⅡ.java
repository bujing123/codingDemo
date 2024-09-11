package com.jd.leetcode.backTrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 *
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 */
public class PermuteⅡ {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    /**
     * 因为每次递归中，没有开始节点，用used来定义在每个树深中第 i 个节点是否已经使用过；如果在树的深度挖掘中，发现已经使用过，则跳过
     * true:已经使用；false：未使用
     *
     *  * 又由于有重复数字，因此在同一树层中定义某个节点是否使用过，如果值相同，并且已经使用过，则不需要在该树层中采集这个节点的list；
     *      * true:已经使用过
     */
    boolean[] used;

    /**
     *
     *              // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
     *             // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过
     *             // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
     * 简而言之：used可以拿来定义，在同一个树层下，如果是使用过这该节点是false；在同一个树深下，一个节点使用过则是true；
     * 所以，
     * 在一个树层时，需要避免相同的节点作为头结点开始时，就对应的值为false时continue；
     * 在一个树深下，需要避免使用过的节点时，则对应的值为true就continue；
     * @param nums
     * @return
     */


    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];
        Arrays.sort(nums);
        backTrace(nums);
        return res;
    }


    void backTrace(int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(used[i]){
                continue;
            }
            if (i>0 && nums[i] == nums[i-1] && !used[i-1]){
                continue;
            }
            used[i] = true;
            list.add(nums[i]);
            backTrace(nums);
            used[i] = false;
            list.remove(list.size()-1);
        }
    }

}
