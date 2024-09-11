package com.jd.leetcode.binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 637. 二叉树的层平均值
 * <p>
 * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
 * <p>
 * 思路：
 * 拿到层序遍历的list后，再算每一层的平均值
 */
public class AverageTree {
    List<List<Integer>> resList = new ArrayList<>();

    public List<Double> averageOfLevels(TreeNode root) {
        recursion(root,0);
        List<Double> list = new ArrayList<>();
        for (List<Integer> res : resList) {
            Double total = 0.0;
            for (Integer i : res) {
                total += i;
            }
            list.add(total/res.size());
        }
        return list;
    }

    private void recursion(TreeNode node, int deep) {
        if(node == null){
            return;
        }

        if(resList.size()<deep+1){
            resList.add(new ArrayList<>());
        }
        resList.get(deep).add(node.val);

        recursion(node.left,deep+1);
        recursion(node.right,deep+1);
    }
}
