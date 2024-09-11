package com.jd.leetcode.binaryTree;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 513. 找树左下角的值
 *
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 *
 * 假设二叉树中至少有一个节点。
 *
 *
 * 递归：
 * 根据层序遍历拿到最后一排的节点，然后直接取出就完了
 *
 */
public class BottomLeftValue {
    List<List<Integer>> list  =new ArrayList<>();
    public int findBottomLeftValue(TreeNode root) {
        recursion(root,0);
        return list.get(list.size()-1).get(0);
    }

    private void recursion(TreeNode root, int deep) {
        if (root == null){
            return;
        }

        if(list.size()<deep+1){
            list.add(new ArrayList<>());
        }
        list.get(deep).add(root.val);

        recursion(root.left,deep+1);
        recursion(root.right,deep+1);
    }


    /**
     * 只需要高度和值的递归法
     */
    private int deepest = -1;
    private int value = 0;

    /**
     * 当前节点和当前节点的深度
     *   当前深度超过标记深度的时候，就直接覆盖value和深度
     * @param node
     * @param deep
     */
    private void recursion2(TreeNode node, int deep){
        if(node == null){
            return;
        }
        if(deep > deepest){
            value = node.val;
            deepest = deep;
        }
        recursion2(node.left, deep+1);
        recursion2(node.right, deep+1);
    }

    public int findBottomLeftValue2(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int result=0;
        while (!deque.isEmpty()) {
            TreeNode peek = deque.peek();
            result = peek.val;
            int size = deque.size();
            while (size-- > 0) {
                TreeNode poll = deque.poll();
                if (poll.left != null) {
                    deque.offer(poll.left);
                }
                if (poll.right != null) {
                    deque.offer(poll.right);
                }
            }
        }
        return result;
    }

}
