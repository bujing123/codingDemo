package com.jd.leetcode.binaryTree;


import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * 98. 验证二叉搜索树
 *
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 思路：
 * 对于每个节点而言，就是判断当前节点的左子树都比当前节点低，右子树都比当前节点值高
 * 二叉搜索树，在中序遍历下是递增的
 */
public class CheckBST {

    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        return inOrder(root,list);
    }


    public boolean inOrder(TreeNode root, List<Integer> list){
        if (root == null){
            return true;
        }
        boolean a = inOrder(root.left, list);
        if (list.size()>0 && list.get(list.size()-1)>root.val){
            return false;
        }else {
            list.add(root.val);
        }
        list.add(root.val);
        boolean b = inOrder(root.right, list);
        return a && b;
    }

    TreeNode max;
    public boolean isValidBST2(TreeNode root){
        if (root == null){
            return true;
        }
        boolean left = isValidBST2(root.left);
        if (!left){
            return false;
        }
        if (max != null && max.val>root.val){
            return false;
        }
        max = root;
        return isValidBST2(root.right);

    }



}
