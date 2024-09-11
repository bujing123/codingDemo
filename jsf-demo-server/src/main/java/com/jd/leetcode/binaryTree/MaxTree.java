package com.jd.leetcode.binaryTree;

/**
 * 654. 最大二叉树
 * <p>
 * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
 * <p>
 * 1、创建一个根节点，其值为 nums 中的最大值。
 * 2、递归地在最大值 左边 的 子数组前缀上 构建左子树。
 * 3、递归地在最大值 右边 的 子数组后缀上 构建右子树。
 * 返回 nums 构建的 最大二叉树 。
 */
public class MaxTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length);
    }

    private TreeNode constructMaximumBinaryTree(int[] nums, int leftIndex, int rightIndex) {
        if (rightIndex - leftIndex == 0) {
            return null;
        }
        if (rightIndex - leftIndex == 1) {
            return new TreeNode(nums[leftIndex]);
        }

        int max = nums[leftIndex];
        int maxIndex = leftIndex;
        for (int i = maxIndex; i < rightIndex; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(max);

        root.left = constructMaximumBinaryTree(nums, leftIndex, maxIndex);
        root.right = constructMaximumBinaryTree(nums, maxIndex + 1, rightIndex);

        return root;

    }

    public static void main(String[] args) {
        String a = "1234567890";
        System.out.println(a.substring(3));
    }


}
