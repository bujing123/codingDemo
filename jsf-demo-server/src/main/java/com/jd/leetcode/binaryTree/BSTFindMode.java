package com.jd.leetcode.binaryTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 501. 二叉搜索树中的众数
 * <p>
 * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
 * <p>
 * 如果树中有不止一个众数，可以按 任意顺序 返回。
 * <p>
 * 假定 BST 满足如下定义：
 * <p>
 * 结点左子树中所含节点的值 小于等于 当前节点的值
 * 结点右子树中所含节点的值 大于等于 当前节点的值
 * 左子树和右子树都是二叉搜索树
 * <p>
 * 思路：
 * 用一个map的key存数据，value存出现次数
 */
public class BSTFindMode {
    Map<Integer, Integer> map = new HashMap<>();
    int max = 0;

    public int[] findMode(TreeNode root) {
        if (root == null) {
            return null;
        }

        recursion(root);
        Set<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(max)) {
                set.add(entry.getKey());
            }
        }
        Integer[] temp = set.toArray(new Integer[set.size()]);
        int[] res = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            res[i] = temp[i].intValue();
        }
        return res;
    }

    private void recursion(TreeNode root) {
        if (root == null) {
            return;
        }
        recursion(root.left);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        max = Math.max(max, map.get(root.val));
        recursion(root.right);
    }

    int maxCount;
    int count;
    HashSet<Integer> setResult = new HashSet<>();
    TreeNode pre;

    /**
     * 利用BST中序遍历有序的性质
     * 遇到相同的就把次数加，然后跟最大的一样的时候就放入set，然后更大了就把set删完，添加；再更新最大值
     *
     * @param root
     * @return
     */
    public int[] findMode2(TreeNode root) {
        if (root == null) {
            return null;
        }
        recursion2(root);
        Integer[] temp = setResult.toArray(new Integer[setResult.size()]);
        int[] res = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            res[i] = temp[i].intValue();
        }
        return res;
    }

    private void recursion2(TreeNode root) {
        if (root == null) {
            return;
        }
        recursion2(root.left);
        if (pre != null) {
            if (pre.val == root.val) {
                count++;
            } else {
                count = 1;
            }
            if (count < maxCount) {

            } else if (count == maxCount) {
                //相同的时候，把当前节点值塞入
                setResult.add(root.val);
            } else {
                setResult.removeAll(setResult);
                setResult.add(root.val);
                maxCount = count;
            }
        } else {
            setResult.add(root.val);
            maxCount = 1;
            count = 1;
        }
        pre = root;

        recursion2(root.right);

    }


}
