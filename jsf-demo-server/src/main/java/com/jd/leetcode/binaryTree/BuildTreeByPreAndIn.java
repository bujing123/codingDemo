package com.jd.leetcode.binaryTree;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 *
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 * 思路：
 * 1、根据前序遍历第一个，拿到当前节点
 * 2、根据节点去中序遍历里面找到左右子树
 * 3、根据左子树长度把前序遍历的左右子树分开
 * 4、递归
 */
public class BuildTreeByPreAndIn {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeByPreAndIn(preorder,0,preorder.length,inorder,0,inorder.length);
    }

    private TreeNode buildTreeByPreAndIn(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {

        if(preStart == preEnd || inStart == inEnd){
            return null;
        }

        int val = preorder[preStart];
        TreeNode root = new TreeNode(val);

        int midIndex;
        for(midIndex = inStart; midIndex<inEnd; midIndex++){
            if(inorder[midIndex] == val){
                break;
            }
        }

        int leftInStart = inStart;
        int leftInEnd = midIndex;
        int rightInStart = midIndex+1;
        int rightInEnd = inEnd;

        int leftPreStart = preStart+1;
        int leftPreEnd = leftPreStart+(leftInEnd-leftInStart);
        int rightPreStart = leftPreEnd;
        int rightPreEnd = preEnd;

        root.left = buildTreeByPreAndIn(preorder,leftPreStart,leftPreEnd,inorder,leftInStart,leftInEnd);
        root.right = buildTreeByPreAndIn(preorder,rightPreStart,rightPreEnd,inorder,rightInStart,rightInEnd);

        return root;
    }
}
