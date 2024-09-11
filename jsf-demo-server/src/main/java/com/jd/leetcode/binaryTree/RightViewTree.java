package com.jd.leetcode.binaryTree;

import com.jd.ob.domain.task.TaskResponse;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 199. 二叉树的右视图
 * <p>
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 思路：
 * 每次层序遍历都将最右边那个加入list中即可
 */
public class RightViewTree {
    List<Integer> resList = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return resList;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            TreeNode peek = deque.peekLast();
            resList.add(peek.val);
            int size = deque.size();
            while (size-- > 0) {
                TreeNode poll = deque.poll();
                if(poll.left != null){
                    deque.offer(poll.left);
                }
                if(poll.right != null){
                    deque.offer(poll.right);
                }
            }
        }
        return resList;
    }
}
