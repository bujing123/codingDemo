package com.jd.leetcode.binaryTree;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 107. 二叉树的层序遍历 II
 * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 跟普通的层序遍历相比就是最后将list的顺序反转一下
 */
public class LevelOrderⅡ {
    List<List<Integer>> resultList = new ArrayList<>();
    List<List<Integer>> tempList = new ArrayList<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return resultList;
        }
        //层序遍历
        iterate(root);
        //反转list
        for (int i = tempList.size() - 1; i >= 0; i--) {
            resultList.add(tempList.get(i));
        }
        return resultList;
    }

    private void iterate(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> item = new ArrayList<>();
            while (size-- > 0) {
                TreeNode poll = deque.poll();
                item.add(poll.val);
                if (poll.left != null) {
                    deque.offer(poll.left);
                }
                if (poll.right != null) {
                    deque.offer(poll.right);
                }
            }
            tempList.add(item);

        }

    }


}
