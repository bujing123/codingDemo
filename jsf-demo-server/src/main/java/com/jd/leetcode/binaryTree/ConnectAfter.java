package com.jd.leetcode.binaryTree;


import java.util.Deque;
import java.util.LinkedList;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 * <p>
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 * 思路：
 * 是不是完全二叉树都无所谓
 * 按照遍历的办法来
 * 1、每次从队列中取出第一个节点的时候记录为pre
 * 2、然后循环往后取节点，每次取出来就将next指向他
 * 3、然后将pre的子节点放入队列中
 * 4、再将pre=now
 */
public class ConnectAfter {

    public static TreeNode connect(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            TreeNode pre = deque.poll();
            if (pre.left != null) {
                deque.offer(pre.left);
            }
            if (pre.right != null) {
                deque.offer(pre.right);
            }
            while (size-- > 1) {
                TreeNode now = deque.poll();
                if (now.left != null) {
                    deque.offer(now.left);
                }
                if (now.right != null) {
                    deque.offer(now.right);
                }
                pre.next = now;
                pre = now;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.setVal(1);
        TreeNode root2 = new TreeNode();
        root2.setVal(2);
        TreeNode root3 = new TreeNode();
        root3.setVal(3);
        TreeNode root4 = new TreeNode();
        root4.setVal(4);
        TreeNode root5 = new TreeNode();
        root5.setVal(5);
        TreeNode root6 = new TreeNode();
        root6.setVal(6);
        TreeNode root7 = new TreeNode();
        root7.setVal(7);
        root.setLeft(root2);
        root.setRight(root3);
        root2.setLeft(root4);
        root2.setRight(root5);
        root3.setLeft(root6);
        root3.setRight(root7);
        connect(root);
    }

}
