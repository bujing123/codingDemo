package com.jd.leetcode.binaryTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 111. 二叉树的最小深度
 * <p>
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明：叶子节点是指没有子节点的节点。
 */
public class MinDeep {
    /**
     * * 递归法：
     * * 边界条件：当前节点为空
     * * 循环逻辑：取左右子节点的最小深度
     * * 最后返回值：需要根据 叶子结点（没有左右子节点才算） 来，如果只有单边为空，就得去取另一个儿子节点的最小深度
     * *
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int right = minDepth(root.right);
        int left = minDepth(root.left);

        if (left == 0) {
            return right + 1;
        } else if (right == 0) {
            return left + 1;
        } else {
            return 1 + Math.min(left, right);
        }
    }

    /**
     * 遍历法
     *
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int deepMin = 0;
        while (!deque.isEmpty()) {
            deepMin++;
            int size = deque.size();
            while (size-- > 0) {
                TreeNode poll = deque.poll();
                if(poll.left == null && poll.right == null){
                    return deepMin;
                }
                if (poll.left != null){
                    deque.offer(poll.left);
                }
                if (poll.right != null){
                    deque.offer(poll.right);
                }
            }
        }
        return deepMin;
    }

}
