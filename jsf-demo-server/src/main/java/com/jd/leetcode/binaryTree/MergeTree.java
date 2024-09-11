package com.jd.leetcode.binaryTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 617. 合并二叉树
 * <p>
 * 给你两棵二叉树： root1 和 root2 。
 * <p>
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。
 * 你需要将这两棵树合并成一棵新二叉树。
 * 合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
 * <p>
 * 返回合并后的二叉树。
 * <p>
 * 注意: 合并过程必须从两个树的根节点开始。
 */
public class MergeTree {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);

        return root;
    }

    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root1);
        deque.offer(root2);
        while (!deque.isEmpty()) {
            TreeNode poll1 = deque.poll();
            TreeNode poll2 = deque.poll();
            poll1.val += poll2.val;
            if (poll1.left != null && poll2.left != null) {
                deque.offer(poll1.left);
                deque.offer(poll2.left);
            }
            if (poll1.right != null && poll2.right != null) {
                deque.offer(poll1.right);
                deque.offer(poll2.right);
            }
            if (poll1.left == null && poll2.left != null) {
                poll1.left = poll2.left;
            }
            if (poll1.right == null && poll2.right != null) {
                poll1.right = poll2.right;
            }


        }
        return root1;
    }
}
