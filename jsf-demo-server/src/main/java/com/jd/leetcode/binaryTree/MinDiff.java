package com.jd.leetcode.binaryTree;


/**
 * 530. 二叉搜索树的最小绝对差
 *
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 *
 * 差值是一个正数，其数值等于两值之差的绝对值。
 *
 * 思路：
 * 二叉搜索树是中序遍历
 * 因此差值就是中序遍历相邻节点之间的差值
 */
public class MinDiff {
    TreeNode pre;
    int min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        if (root == null){
            return 0;
        }
        recursion(root);
        return min;
    }

    /**
     * 中序遍历就是 先查左边，然后做递归的循环逻辑，然后查右边
     * @param root
     */
    private void recursion(TreeNode root) {
        if(root == null){
            return;
        }
        recursion(root.left);
        if (pre != null){
            min = Math.min(min,root.val- pre.val);
        }
        pre = root;
        recursion(root.right);
    }
}
