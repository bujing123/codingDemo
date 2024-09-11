package com.jd.leetcode.binaryTree;

import java.util.*;

/**
 * 113. 路径总和 II
 * <p>
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 */
public class PathSumⅡ {
    List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return resultList;
        }
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        pathSum11(root, list, sum, targetSum);
        return resultList;
    }

    private void pathSum11(TreeNode root, List<Integer> list, int sum, int targetSum) {
        sum += root.val;
        list.add(root.val);
        if (sum == targetSum && root.left == null && root.right == null) {
            resultList.add(new ArrayList<>(list));
        }
        if (root.left != null) {
            pathSum11(root.left, list, sum, targetSum);
            list.remove(list.size() - 1);
        }
        if (root.right != null) {
            pathSum11(root.right, list, sum, targetSum);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        if(root == null){
            return resultList;
        }
        Deque<Object> deque = new LinkedList<>();
        List<Integer> list = Arrays.asList(root.val);
        deque.offer(root);
        deque.offer(list);
        deque.offer(root.val);
        while (!deque.isEmpty()){
            int size = deque.size();
            int i = size / 3;
            while (i-- > 0) {
                TreeNode poll = (TreeNode) deque.poll();
                List<Integer> pollList = (List<Integer>) deque.poll();
                Integer poll2 = (Integer) deque.poll();
                if (poll.left == null && poll.right == null && poll2 == targetSum){
                    resultList.add(pollList);
                    continue;
                }
                if (poll.left != null){
                    deque.offer(root.left);
                    List<Integer> arrayList = new ArrayList<>(pollList);
                    arrayList.add(poll.left.val);
                    deque.offer(arrayList);
                    deque.offer(poll2+poll.left.val);
                }
                if (poll.right != null){
                    deque.offer(root.right);
                    List<Integer> arrayList = new ArrayList<>(pollList);
                    arrayList.add(poll.right.val);
                    deque.offer(arrayList);
                    deque.offer(poll2+poll.right.val);
                }
            }
        }
        return resultList;

    }
}

