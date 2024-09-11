package com.jd.leetcode.binaryTree;

/**
 * 572. 另一棵树的子树
 * <p>
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
 * <p>
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 * <p>
 * 思路：
 * 递归，先分为3种情况，subRoot是当前节点的相同树，subRoot是当前节点的左右树
 */
public class OtherSonTree {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot != null) {
            return false;
        }
        return ifSameTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    /**
     * 相同数递归
     * 边界条件：
     * 值不相同返回false
     * 都为空返回true
     *
     * @param root
     * @param subRoot
     * @return
     */
    private boolean ifSameTree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null && subRoot != null) {
            return false;
        }
        if (root != null && subRoot == null) {
            return false;
        }
        if (root.val != subRoot.val) {
            return false;
        }
        return ifSameTree(root.left, subRoot.left) && ifSameTree(root.right, subRoot.right);
    }


}
