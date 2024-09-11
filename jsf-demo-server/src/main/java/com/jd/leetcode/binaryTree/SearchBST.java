package com.jd.leetcode.binaryTree;


import java.util.Deque;
import java.util.LinkedList;

/**
 * 700. 二叉搜索树中的搜索
 * 给定二叉搜索树（BST）的根节点 root 和一个整数值 val。
 * <p>
 * 你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null 。
 * <p>
 * 二叉搜索树，左边比根节点大，右子树比根节点小
 * <p>
 * <p>
 * 二叉搜索树是一个有序树：
 * <p>
 * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 * 它的左、右子树也分别为二叉搜索树
 */
public class SearchBST {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        if (root.val > val) {
            return searchBST(root.left, val);
        }
        return searchBST(root.right, val);
    }

    /**
     * 根据需不需要每一层来单独判断的逻辑，这道题明显没有一层单独的逻辑，因此不用两重循环
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST2(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            TreeNode poll = deque.poll();
            if (poll.val == val) {
                return poll;
            }
            if (poll.val > val && poll.left != null) {
                deque.offer(poll.left);
            }
            if (poll.val < val && poll.right != null) {
                deque.offer(poll.right);
            }
        }
        return null;
    }

}
