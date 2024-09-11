package com.jd.leetcode.binaryTree;

/**
 * 538. 把二叉搜索树转换为累加树
 * <p>
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
 * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * <p>
 * 提醒一下，二叉搜索树满足下列约束条件：
 * <p>
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * <p>
 * <p>
 * 思路：
 * 第一种：计算好所有的累加值后，按照中序遍历的顺序，挨个递减；
 * 第二种：反着中序遍历的顺序来，挨个递增
 */
public class ConvertBST {

    TreeNode pre;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        if (pre != null) {
            root.val = root.val + pre.val;
        }
        pre = root;
        convertBST(root.left);
        return root;
    }



    /**
     * 累减
     *
     * @param root
     * @return
     */
    int total = 0;
    public TreeNode convertBST2(TreeNode root) {
        getTotal(root);
        recursion(root);
        return root;
    }

    private void recursion(TreeNode root) {
        if (root == null) {
            return;
        }
        recursion(root.left);
        if (pre == null) {
            root.val = total - root.val;
        } else {
            root.val = pre.val - root.val;
        }
        pre = root;
        recursion(root.right);
    }

    private void getTotal(TreeNode root) {
        total += root.val;
        if (root.left != null) {
            getTotal(root.left);
        }
        if (root.right != null) {
            getTotal(root.right);
        }
    }

}
