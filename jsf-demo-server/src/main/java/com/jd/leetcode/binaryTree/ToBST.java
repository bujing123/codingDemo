package com.jd.leetcode.binaryTree;


/**
 * 108. 将有序数组转换为二叉搜索树
 * <p>
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 二叉搜索树
 * <p>
 * 思路：
 * 对于每一个节点来说，都是从开始和结尾的中间选取值，然后分割开的左边当左子树，右边当右子树，最后返回当前节点，结束条件就是二分法的条件，开始大于等于结尾
 * 1、每次把给的数组中间的数据作为节点返回
 * 2、
 */
public class ToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        return toBST(nums, 0, nums.length);
    }

    private TreeNode toBST(int[] nums, int start, int end) {
        if (start >= end) {
            return null;
        }
//        if (end - start == 1){
//            return new TreeNode(nums[start]);
//        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = toBST(nums,start,mid);
        root.right = toBST(nums,mid+1,end);
        return root;
    }

}
