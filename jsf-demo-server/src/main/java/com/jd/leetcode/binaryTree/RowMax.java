package com.jd.leetcode.binaryTree;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 515. 在每个树行中找最大值
 * <p>
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 * <p>
 * 思路：
 * 1、按照层序遍历来
 * 2、每一层取一个最大值，然后每次用Math.max来获得最大值
 */
public class RowMax {
    List<Integer> resList = new ArrayList<>();

    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return resList;
        }
        // 递归法
        recursion(root, 0);
        //遍历法
        iterate(root);
        return resList;
    }

    /**
     * 循环遍历法，将节点放到队列中，每次推出老节点，放入老节点的子节点，一直推到所有节点推出
     *
     * @param root
     */
    private void iterate(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            Integer max = null;
            while (size-- > 0) {
                TreeNode poll = deque.poll();
                if (max == null) {
                    max = poll.val;
                } else {
                    max = Math.max(poll.val, max);
                }
                if (poll.left != null) {
                    deque.offer(poll.left);
                }
                if (poll.right != null) {
                    deque.offer(poll.right);
                }
            }
            resList.add(max);
        }
    }

    /**
     * 递归法：
     * 1、边界：节点为空
     * 2、循环递归的入参：左子节点和右子节点
     * 3、每次的逻辑，list的下标为第几行，每次比较最大值往里面插入，如果没有值就直接放当前值
     *
     * @param root
     */
    private void recursion(TreeNode root, Integer deep) {
        if (root == null) {
            return;
        }

        if (resList.size() < deep + 1) {
            //此时说明还没有值
            resList.add(root.val);
        } else {
            //此时说明已经有值了，直接比较大小
            resList.set(deep, Math.max(root.val, resList.get(deep)));
        }

        recursion(root.left, deep + 1);
        recursion(root.right, deep + 1);
    }
}
