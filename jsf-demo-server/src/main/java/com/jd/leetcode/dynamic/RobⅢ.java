package com.jd.leetcode.dynamic;


import com.jd.leetcode.binaryTree.TreeNode;

/**
 * 337. 打家劫舍 III
 * <p>
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * <p>
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * <p>
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 * <p>
 * 树形动态规划
 * 对每一个节点定义，dp[i,j ] i 表示偷当前节点的最大值，j 表示不偷当前节点的最大值
 * dp[i,j]: i = 当前节点值 + 左右子树不偷左右子树的最大值  j =  左右子树分别的最大值
 * 递归顺序，先递归左右子树，再递归当前节点，后序递归
 * 举例
 */
public class RobⅢ {

    public int rob(TreeNode root) {

        int[] dp = robNode(root);
        return Math.max(dp[0], dp[1]);

    }

    /**
     * 下标0表示偷当前节点；下标1表示不偷当前节点
     *
     * 递归三要素：
     * 1、结束条件
     * 2、递归顺序：后序
     * 3、逻辑，递归左右子树，然后判断当前的节点取得数组返回
     *
     * @param node
     * @return
     */
    public int[] robNode(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = robNode(node.left);
        int[] right = robNode(node.right);

        //偷当前节点的最大值
        int value1 = node.val + left[1] + right[1];
        //不偷
        int value2 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{value1, value2};
    }


}
