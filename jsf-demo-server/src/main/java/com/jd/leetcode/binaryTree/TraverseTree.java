package com.jd.leetcode.binaryTree;


import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的遍历
 */
public class TraverseTree {

    /**
     * 遍历二叉树
     * @param root
     * @return
     */
    public List<Integer> traversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        //前序遍历
        preorderTraversal(root,list);
        //后序遍历
        postorderTraversal(root,list);
        //中序遍历
        inorderTraversal(root,list);
        return list;
    }


    /**
     * 144.二叉树的前序遍历
     *
     */
    public void preorderTraversal(TreeNode root, List<Integer> list){
        if(root == null){
            return;
        }
        list.add(root.val);
        preorderTraversal(root.left,list);
        preorderTraversal(root.right,list);

    }

    /**
     * 145.二叉树的后序遍历
     */
    public void postorderTraversal(TreeNode root, List<Integer> list){
        if(root == null){
            return;
        }
        postorderTraversal(root.left,list);
        postorderTraversal(root.right,list);
        list.add(root.val);
    }

    /**
     * 94.二叉树的中序遍历
     */
    public void inorderTraversal(TreeNode root, List<Integer> list){
        if(root == null){
            return;
        }
        inorderTraversal(root.left,list);
        list.add(root.val);
        inorderTraversal(root.right,list);
    }
}
