package com.jd.leetcode.binaryTree;

/**
 * 100. 相同的树
 * <p>
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * 思路：
 * 递归：
 * 边界条件：
 * 1、不相等了直接返回false
 * 重复逻辑：
 * 判断左子树是否相同，判断右子树是否相同
 * 最后返回
 * left && right
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }
        if (p == null && q == null){
            return true;
        }
        if (p.val != q.val) {
            return false;
        }


        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);

        return left && right;

    }

}
