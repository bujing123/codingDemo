package com.jd.leetcode.binaryTree;

import java.util.*;

/**
 * 102. 二叉树的层序遍历
 * <p>
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 思路：
 * 1、递归遍历：
 * a、递归边际条件：遍历到为空遍返回
 * b、拿出去下一次递归的入参，树的左子树和右子树
 * c、每次递归的逻辑是，将当前递归到的值根据深度加到list中
 * <p>
 * 2、迭代遍历：
 * 每次迭代把当前迭代到的节点推出，他的子节点推入队列
 */
public class LevelOrder {
    List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        recursion(root, 0);
        iterate(root);
        return resultList;
    }

    /**
     * 迭代法
     *
     * @param root
     */
    private void iterate(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        if (root != null) {
            deque.offer(root);
        }
        //以树节点为循环条件，总有循环结束的时候
        while (!deque.isEmpty()) {
            List<Integer> itemList = new ArrayList<>();
            int size = deque.size();
            while (size-- > 0) {
                TreeNode tempNode = deque.poll();
                itemList.add(tempNode.val);
                if (tempNode.left != null) {
                    deque.offer(tempNode.left);
                }
                if (tempNode.right != null) {
                    deque.offer(tempNode.right);
                }
            }
            resultList.add(itemList);
        }
    }


    /**
     * 迭代法
     *
     * @param root
     */
    private void iterate11(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        if(deque.isEmpty()){
            return;
        }
        deque.offer(root);
        while (!deque.isEmpty()) {
            List<Integer> item = new ArrayList<>();
            int size = deque.size();
            while (size-->0){
                TreeNode poll = deque.poll();
                item.add(poll.val);
                if(poll.left != null){
                    deque.offer(poll.left);
                }
                if(poll.right != null){
                    deque.offer(poll.right);
                }
            }
            resultList.add(item);
        }
    }

    /**
     * 每次递归遍历到的节点 还有当前深度
     *
     * @param node
     * @param i
     */
    private void recursion(TreeNode node, int i) {
        if (node == null) {
            return;
        }

        if (resultList.size() < i + 1) {
            resultList.add(new ArrayList<Integer>());
        }
        resultList.get(i).add(node.val);

        recursion(node.left, i + 1);
        recursion(node.right, i + 1);
    }
}
