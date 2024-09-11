package com.jd.leetcode.binaryTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 104. 二叉树的最大深度
 * <p>
 * 给定一个二叉树 root ，返回其最大深度。
 * <p>
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 思路：
 * 1、层序遍历直接解决
 */
public class DeepMax {
    public int maxDepth(TreeNode root) {
        return recursionDeep(root, 0);
    }

    private int recursionDeep(TreeNode root, int deep) {
        if (root == null) {
            return deep;
        }
        int deepest = deep;

        deepest = Math.max(recursionDeep(root.left, deep + 1), deepest);
        deepest = Math.max(recursionDeep(root.right, deep + 1), deepest);

        return deepest;
    }

    private int iterateDeep(TreeNode root){
        if(root == null){
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int deepest = 0;
        while (!deque.isEmpty()){
            int size = deque.size();
            deepest++;
            while (size-->0){
                TreeNode poll = deque.poll();
                if(poll.left != null){
                    deque.offer(poll.left);
                }
                if(poll.right != null){
                    deque.offer(poll.right);
                }
            }
        }
        return deepest;
    }

    /**
     * 边界条件：到节点为空
     * 相同逻辑，求子节点的深度
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root){
        if (root == null){
            return 0;
        }
        return Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;
    }
}
