package com.jd.leetcode.linkedList;

/**
 * 203. 移除链表元素
 * <p>
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 */
public class DeleteLinkedElement {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode node = dummy;
        while (node != null && node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
            }else{
                node = node.next;
            }

        }


        return dummy.next;
    }
}


