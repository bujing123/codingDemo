package com.jd.leetcode.binaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 429. N 叉树的层序遍历
 *
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 *
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 *
 * 思路：
 * 跟层序遍历相同，先将头结点放到队列中，然后遍历队列，每次将遍历节点的子节点存到队列中，再根据队列的 size 循环将上一层的节点弹出来
 * 相当于每轮while循环中，弹出上一层的节点，弹入这一层的节点
 */
public class LevelOrderN {
    List<List<Integer>> resList = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return resList;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while(!deque.isEmpty()){
            List<Integer> itemList = new ArrayList<>();
            int size = deque.size();
            while (size-- > 0){
                TreeNode poll = deque.poll();
                itemList.add(poll.val);
                for (TreeNode child : poll.children) {
                    if(child != null){
                        deque.offer(child);
                    }
                }
            }
            resList.add(itemList);
        }
        return resList;
    }
}
