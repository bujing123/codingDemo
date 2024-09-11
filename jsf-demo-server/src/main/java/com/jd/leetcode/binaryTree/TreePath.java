package com.jd.leetcode.binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 257. 二叉树的所有路径
 * <p>
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * <p>
 * 思路：
 * 递归回溯法：
 * 采用一个list<Integer>来存储路线上面的所有节点，每次递归结束回溯到原本这个地方的时候，
 * 删除最新的当前节点，因为每次递归开始就会存储进去一个新的，递归回来后，递归方法里面的都需要删除，才能去走下一条路线
 * 递归结束就是：遇到叶子节点，那就说明结束了
 * <p>
 * 迭代法：
 * 每次把有新的路线的值和路线图全部压入栈中，当遇到叶子节点的时候就直接存
 */
public class TreePath {

    /**
     * 采用回溯的办法，前序遍历时
     * 每次读完左节点，然后回到原节点，就直接把前面的左节点删除即可；右节点亦如此
     * 然后边际条件就是当遇到叶子节点的时候，就把数据存进去，然后返回
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return null;
        }
        //存放结果
        List<String> resList = new ArrayList<>();
        //存放路线里面的节点
        List<Integer> pathList = new ArrayList<>();
        reverse(resList, pathList, root);
        return resList;
    }

    private void reverse(List<String> resList, List<Integer> pathList, TreeNode root) {
        pathList.add(root.val);

        if (root.left == null && root.right == null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < pathList.size() - 1; i++) {
                stringBuilder.append(pathList.get(i)).append("->");
            }
            stringBuilder.append(pathList.get(pathList.size() - 1));
            resList.add(stringBuilder.toString());
        }

        if (root.left != null) {
            reverse(resList, pathList, root.left);
            pathList.remove(pathList.size() - 1);
        }
        if (root.right != null) {
            reverse(resList, pathList, root.right);
            pathList.remove(pathList.size() - 1);
        }
    }

    /**
     * 迭代法
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        Stack<Object> stack = new Stack<>();
        List<String> resList = new ArrayList<>();
        stack.push(root);
        stack.push(root.val + "");
        while (!stack.isEmpty()) {

            String value = (String) stack.pop();
            TreeNode node = (TreeNode) stack.pop();
            if (node.left == null && node.right == null) {
                resList.add(value);
            }
            if (node.left != null) {
                stack.push(node.left);
                stack.push(value + "->" + node.left.val);
            }
            if (node.right != null) {
                stack.push(node.right);
                stack.push(value + "->" + node.right.val);
            }
        }
        return resList;


    }


}
