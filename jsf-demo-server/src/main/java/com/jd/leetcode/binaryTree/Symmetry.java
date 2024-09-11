package com.jd.leetcode.binaryTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 101. 对称二叉树
 * <p>
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * <p>
 * 思路：
 * 递归法：
 * 相同的逻辑模型：1、每次比较两个节点是否相同；然后比较左的左和右的右，左的右和右的左
 * 遍历法：
 *
 */
public class Symmetry {
    /**
     * 递归法
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return ifSame(root.left, root.right);
    }

    public boolean ifSame(TreeNode left, TreeNode right) {
        //边界条件：
        if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        } else if (left == null && right == null) {
            return true;
        } else if (left.val != right.val) {
            return false;
        }

        //相同逻辑处理：判断两个节点的子节点
        boolean b = ifSame(left.left, right.right);
        boolean b1 = ifSame(left.right, right.left);

        return b && b1;

    }


    /**
     * 遍历法
     * 使用一个双端队列，每次将子节点的左右节点放出去，然后每次都比较前后各取一个出来的值，一直到最后取完如果都没有不相同的，那说明就是对称的
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        if (root.left == null && root.right == null){
            return true;
        }
        if(root.left == null || root.right == null){
            return false;
        }
        deque.offerFirst(root.left);
        deque.offerLast(root.right);
        while (!deque.isEmpty()){
            int size = deque.size();
            int frequency = size / 2;
            //每次弹出来两个
            while (frequency-->0){
                TreeNode nodeFirst = deque.pollFirst();
                TreeNode nodeLast = deque.pollLast();
                if(nodeFirst == null && nodeLast != null){
                    return false;
                }else if(nodeFirst != null && nodeLast == null){
                    return false;
                }else if(nodeFirst == null && nodeLast == null){
                    continue;
                }else if (nodeFirst.val != nodeLast.val){
                    return false;
                }
                deque.offerFirst(nodeFirst.left);
                deque.offerLast(nodeLast.right);
                deque.offerFirst(nodeFirst.right);
                deque.offerLast(nodeLast.left);
            }
        }
        return true;
    }
}
