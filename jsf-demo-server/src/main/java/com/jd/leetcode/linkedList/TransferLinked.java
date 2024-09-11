package com.jd.leetcode.linkedList;

/**
 * 24. 两两交换链表中的节点
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * <p>
 * <p>
 * 递归需要满足两个条件，1、一个充分的边界条件，达到这个条件后直接返回 2、可以反复执行的逻辑
 * 因此递归的方法可以这样子总结写
 * 1、写返回的边界条件
 * 2、写拿去递归的入参
 * 3、写递归达到边界条件后，返回回来的出参使用的通用逻辑
 */
public class TransferLinked {
    public ListNode swapPairs(ListNode head) {
        //第一步 写边界条件
        if (head == null || head.next == null) {
            return head;
        }
        //第二步，写拿去递归的入参和出参
        ListNode next = head.next;
        ListNode node = swapPairs(next.next);//因为每两两交换，相当于每两个做一次这种操作，因此如此使用递归，递归结果就是后边新的已经递归结束的新的head点

        //第三步，每次递归中两两交换的通用逻辑
        next.next = head;
        head.next = node;

        return next;
    }

    public ListNode swapPairs3(ListNode head) {
        if (head == null){
            return null;
        }

        ListNode next = head.next;
        ListNode node = swapPairs3(next.next);

        next.next = head;
        head.next = node;

        return next;
    }


    /**
     * 这种办法就是设置虚拟节点，一步一步正常替换
     *
     * @param head
     * @return
     */
    public ListNode swapPairs2(ListNode head) {
        ListNode dumyhead = new ListNode(-1); // 设置一个虚拟头结点
        dumyhead.next = head; // 将虚拟头结点指向head，这样方便后面做删除操作
        ListNode cur = dumyhead;
        ListNode temp; // 临时节点，保存两个节点后面的节点
        ListNode firstnode; // 临时节点，保存两个节点之中的第一个节点
        ListNode secondnode; // 临时节点，保存两个节点之中的第二个节点
        while (cur.next != null && cur.next.next != null) {
            temp = cur.next.next.next;
            firstnode = cur.next;
            secondnode = cur.next.next;
            cur.next = secondnode;       // 步骤一
            secondnode.next = firstnode; // 步骤二
            firstnode.next = temp;      // 步骤三
            cur = firstnode; // cur移动，准备下一轮交换
        }
        return dumyhead.next;
    }
}
