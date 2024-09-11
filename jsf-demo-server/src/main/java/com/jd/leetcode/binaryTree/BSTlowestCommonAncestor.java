package com.jd.leetcode.binaryTree;

/**
 * 235. 二叉搜索树的最近公共祖先
 * <p>
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 *
 * 思路：
 * 二叉搜索树是有序的，自顶向下查找就可以了，如果一左一右那就说明当前节点是最近祖先；如果都在左边就去左边找；都在右边就去右边找
 */
public class BSTlowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }

        if ((root.val > p.val && q.val > root.val) || (root.val<p.val && root.val>q.val)) {
            return root;
        }else if(root.val>q.val && root.val>q.val){
            return lowestCommonAncestor(root.left,p,q);
        }else {
            return lowestCommonAncestor(root.right,p,q);
        }



    }


}
