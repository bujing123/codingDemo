package com.jd.leetcode.binaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 222. 完全二叉树的节点个数
 * <p>
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * <p>
 * 完全二叉树 的定义如下：
 * 在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
 * 若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * <p>
 * 思路：
 * 1、递归法
 * 按照前序、中序、后序、层序遍历任意一个遍历办法的时候，遇到一个节点就count++即可
 * 递归：
 * 求左子树的节点+右子树的节点+1，就是所有节点数量
 * <p>
 * 2、按照层序遍历的迭代遍历法，每次堆栈增加就+1即可
 */
public class CountNode {
    public int countNodes(TreeNode root) {
        //前序遍历
//        List<Integer> list = new ArrayList<>();
//        recursion(root,list);

        //层序遍历
        if (root == null) {
            return 0;
        }

        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    private void recursion(TreeNode root, List<Integer> count) {
        //前序遍历
        if (root == null) {
            return;
        }
        count.add(root.val);
        recursion(root.left, count);
        recursion(root.right, count);
    }


    public int countNodes2(TreeNode root) {
        if(root == null){
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int count = 0;
        while (!deque.isEmpty()){
            int size = deque.size();
            while (size-->0){
                TreeNode poll = deque.poll();
                count++;
                if(poll.left != null){
                    deque.offer(poll.left);
                }
                if(poll.right != null){
                    deque.offer(poll.right);
                }
            }
        }
        return count;
    }
}
