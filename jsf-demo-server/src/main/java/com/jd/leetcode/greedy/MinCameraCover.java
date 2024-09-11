package com.jd.leetcode.greedy;


import com.jd.leetcode.binaryTree.TreeNode;

/**
 * 968. 监控二叉树
 * <p>
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 * <p>
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * <p>
 * 计算监控树的所有节点所需的最小摄像头数量。
 */
public class MinCameraCover {
    int max = 0;

    public int minCameraCover(TreeNode root) {
        int traversal = traversal(root);
        if (traversal == 0){
            max++;
        }
        return max;
    }

    /**
     * @param root
     * @return 0：代表不在覆盖范围内；1：代表在本节点有摄像头；2：代表本行没有摄像头，但是在监控范围内
     */
    public int traversal(TreeNode root) {
        //结束条件
        if (root == null) {
            return 2;
        }
        //遍历左子树、遍历右子树
        int left = traversal(root.left);
        int right = traversal(root.right);

        //逻辑处理
        if (left == 2 && right == 2) {  //两个子节点都在范围内，但是没有摄像头，说明当前节点是不在范围内，并且没有摄像头
            return 0;
        } else if (left == 0 || right == 0) { //说明至少有一个子节点没有在范围内，那么此节点就必须放置
            max++;
            return 1;
        } else if (left == 1 || right == 1) {  //两个子节点有一个有摄像头，说明本节点已经在范围内了，但是没有摄像头
            return 2;
        }
        return 2;
    }


}
