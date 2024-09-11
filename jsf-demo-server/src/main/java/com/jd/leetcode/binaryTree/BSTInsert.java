package com.jd.leetcode.binaryTree;

/**
 * 701. 二叉搜索树中的插入操作
 *
 * 给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。
 * 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
 *
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 *
 *
 */
public class BSTInsert {
    TreeNode pre;

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null){
            TreeNode node = new TreeNode(val);
            if (pre != null){
                if (node.val>pre.val){
                    pre.right = node;
                }else {
                    pre.left = node;
                }
            }else {
                root = node;
            }
            return root;
        }
        pre = root;
        if (root.val>val){
            insertIntoBST(root.left,val);
        }else if(root.val<val){
            insertIntoBST(root.right,val);
        }

        return root;
    }

}
