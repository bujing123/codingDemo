package com.jd.leetcode.linkedList;

/**
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class ReverseLinked {
    /**
     * 定义双指针，一个指针虚拟指针，一个指针为循环指针，循环指针一直指向前一个
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode aft = cur.next;
            cur.next = pre;
            pre = cur;
            cur = aft;
        }
        return cur;
    }

    /**
     * 逆向递归，递归到最后，直接返回最后的节点
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode last = reverseList2(head.next);

        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 正向递归
     */
    public ListNode reverseList3(ListNode head) {
        return reverse(null, head);
    }

    private ListNode reverse(ListNode head, ListNode next) {
        if (next == null) {
            return head;
        }
        ListNode aft = next.next;
        next.next = head;
        return reverse(next, aft);
    }

    /**
     * 创造一个新的链表，每次创造新的链表头
     */
    public ListNode reverseList4(ListNode head) {
        ListNode newHead = null;
        ListNode dummy = head;
        while (dummy != null){
            ListNode next = dummy.next;
            dummy.next = newHead;
            newHead = dummy;
            dummy = next;
        }

        return newHead;
    }
}
