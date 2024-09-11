package com.jd.leetcode.linkedList;


/**
 *  链表解题经典三板斧，哑巴节点，栈，快慢指针
 */

/**
 * 19. 删除链表的倒数第N个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class deleteBackNElement {
    /**
     * 双指针，一个先走n步，然后两个指针一起走，等先走的走到结尾，
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode fast = dummy;
        for(int i=0; i<=n; i++){
            fast = fast.next;
        }
        ListNode slow = dummy;
        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return head;
    }
}
