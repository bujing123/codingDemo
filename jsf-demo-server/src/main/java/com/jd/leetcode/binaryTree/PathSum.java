package com.jd.leetcode.binaryTree;

import java.util.Stack;

/**
 * 112. 路径总和
 * <p>
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。
 * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 * 如果存在，返回 true ；否则，返回 false 。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * <p>
 * 递归思路：
 * 边界条件：节点的左子节点和右子节点为空时，就说明遇到叶子了，此时就已经是一条完整路线了
 * 重复逻辑：往下一直找到左或者右的叶子结点
 * 回溯逻辑：每次回溯过来需要减去加进去的值
 */
public class PathSum {

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        int sum = 0;
        return getSum(root, sum, targetSum);
    }

    private static boolean getSum(TreeNode root, int sum, int targetSum) {
        sum += root.val;
        if (root.left == null && root.right == null) {
            if (sum == targetSum) {
                return true;
            }
        }
        boolean sumLeft = false, sumRight = false;
        if (root.left != null) {
            sumLeft = getSum(root.left, sum, targetSum);
        }

        if (root.right != null) {
            sumRight = getSum(root.right, sum, targetSum);
        }

        return sumLeft || sumRight;
    }

    /**
     * 堆栈操作
     * 将当前值和节点都压入栈，然后遇到左右节点都为空的时候，取出来比较就可以
     * @param
     */
    public static boolean hasPathSum2(TreeNode root, int targetSum){
        if(root == null){
            return false;
        }
        Stack<Object> stack = new Stack<>();
        stack.push(root);
        stack.push(root.val);
        while (!stack.isEmpty()) {
            int size = stack.size();
            int i = size / 2;
            while (i-- > 0) {
                int value = (int) stack.pop();
                TreeNode node = (TreeNode) stack.pop();
                if (node.left == null && node.right == null) {
                    if (value == targetSum) {
                        return true;
                    }
                }
                if (node.left != null) {
                    stack.push(node.left);
                    stack.push(value+ node.left.val);
                }
                if (node.right != null) {
                    stack.push(node.right);
                    stack.push(value+ node.right.val);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode ro = new TreeNode();
        ro.setVal(0);
        TreeNode r1 = new TreeNode();
        r1.setVal(1);
        TreeNode r2 = new TreeNode();
        r2.setVal(1);
        ro.setLeft(r1);
        ro.setRight(r2);
        hasPathSum(ro,0);
    }

}
