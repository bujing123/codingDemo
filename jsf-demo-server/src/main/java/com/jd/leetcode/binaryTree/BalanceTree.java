package com.jd.leetcode.binaryTree;

/**
 * 110. 平衡二叉树
 * <p>
 * 给定一个二叉树，判断它是否是
 * 平衡二叉树
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * 思路：
 * 每次判断节点的左右子树的高度是否超过1
 * 方法：求子树高度的方法
 */
public class BalanceTree {
    public boolean isBalanced(TreeNode root) {
        return deepDiff(root) == -1 ? false : true;
    }

    /**
     * 用-1表示是否为非平衡二叉树，用正数来表示他的高度
     * <p>
     * * 边界条件：
     * * node == null
     * * 重复逻辑：
     * * 算左子树和右子树的深度，并根据深度来判断逻辑
     * * 返回值是当前节点的高度 = 子树最大高度+1
     *
     * @param root
     * @return
     */
    private int deepDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int deepLeft = deepDiff(root.left);
        int deepRight = deepDiff(root.right);
        if (deepLeft == -1 || deepRight == -1) {
            return -1;
        }
        if (Math.abs(deepLeft - deepRight) > 1) {
            return -1;
        }

        return Math.max(deepLeft, deepRight) + 1;
    }
}
