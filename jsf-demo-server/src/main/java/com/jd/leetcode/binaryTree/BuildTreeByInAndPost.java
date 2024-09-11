package com.jd.leetcode.binaryTree;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * <p>
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历，
 * postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 * <p>
 * 思路：
 * 1、根据后序遍历的数组最后一个元素决定是当前根节点
 * 2、拿当前根结点的值去对比中序遍历里面的位置，可以分别拿到左右子树的中序遍历
 * 3、根据拿到的左子树大小，去把后序遍历也分为左右子树的后序遍历
 * 4、递归调用左子树和右子树
 */
public class BuildTreeByInAndPost {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreeByInAndPost(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    private TreeNode buildTreeByInAndPost(int[] inorder, int inStart, int inEnd, int[] postorder, int posStart, int posEnd) {
        if (inStart == inEnd || posStart == posEnd) {
            return null;
        }

        int nodeVal = postorder[posEnd - 1];
        TreeNode root = new TreeNode(nodeVal);

        int inMidIndex;
        for (inMidIndex = inStart; inMidIndex < inEnd; inMidIndex++) {
            if (inorder[inMidIndex] == nodeVal) {
                break;
            }
        }

        int leftInStart = inStart;
        int leftInEnd = inMidIndex;
        int rightInStart = inMidIndex + 1;
        int rightInEnd = inEnd;

        int leftPostStart = posStart;
        int leftPostEnd = posStart + (leftInEnd - leftInStart);
        int rightPostStart = leftPostEnd;
        int rightPostEnd = posEnd - 1;

        root.left = buildTreeByInAndPost(inorder, leftInStart, leftInEnd, postorder, leftPostStart, leftPostEnd);
        root.right = buildTreeByInAndPost(inorder, rightInStart, rightInEnd, postorder, rightPostStart, rightPostEnd);

        return root;
    }
}
