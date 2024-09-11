package com.jd.leetcode.binaryTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 404. 左叶子之和
 *
 * 给定二叉树的根节点 root ，返回所有左叶子之和。
 *
 * 递归:
 * 边界条件：当前节点为空
 * 重复逻辑，计算左节点的左叶子值+右节点的左叶子值
 * 左叶子值：当前节点有左节点，并且左节点没有子节点
 *
 */
public class LeftLeafSum {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null){
            return 0;
        }

        int left = sumOfLeftLeaves(root.left);
        int right = sumOfLeftLeaves(root.right);

        int num = 0;
        if(root.left != null && root.left.left == null && root.left.right == null){
            num = root.left.val;
        }

        return num+left+right;



    }

    /**
     * 遍历，每次把左右子树都压入队列，然后判断当前节点的左子树如果不为空就把左子节点的值加入
     * @param root
     * @return
     */
    public int sumOfLeftLeaves2(TreeNode root){
        if(root == null){
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int sum = 0;
        while (!deque.isEmpty()){
            int size = deque.size();
            while (size-- > 0) {
                TreeNode poll = deque.poll();
                if (poll.left!=null){
                    if(poll.left.left == null && poll.left.right == null){
                        sum += poll.left.val;
                    }
                    deque.offer(poll.left);
                }
                if (poll.right != null) {
                    deque.offer(poll.right);
                }
            }
        }
        return sum;
    }

}
