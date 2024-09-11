package com.jd.leetcode.binaryTree;

/**
 * 450. 删除二叉搜索树中的节点
 * <p>
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * <p>
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * <p>
 * 思路：
 * 一共五种情况，
 * 1、找不到对应的值
 * 2、找到对应值，没有子节点；
 * 3、找到，没有左子节点
 * 4、找到，没有右子节点
 * 5、找到，都有
 */
public class DeleteBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.left != null && root.right == null) {
                return root.left;
            }
            if (root.left == null && root.right != null) {
                return root.right;
            }
            if (root.left != null && root.right != null) {
                //此时有两种办法，一个把左子节点，全部移动到右子树里面最小的值；
                // 第二种办法，把右子树最小的值，来代替被删除的这个节点，然后把右子树最小的那个节点删除
                TreeNode cur = root.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
//                cur.left = root.left;
//                return root.right;
                root.val = cur.val;
                root.right = deleteNode(root.right, cur.val);


                return root;
            }
        }
        root.left = deleteNode(root.left, key);
        root.right = deleteNode(root.right, key);
        return root;
    }
}
