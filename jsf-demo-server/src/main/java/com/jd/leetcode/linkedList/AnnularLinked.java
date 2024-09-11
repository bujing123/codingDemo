package com.jd.leetcode.linkedList;

/**
 * 142. 环形链表II
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 不允许修改 链表。
 */
public class AnnularLinked {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow){
                //此时的slow是快慢指针在环内相遇点
                //此时定义一个指针从head走，一个指针从相遇节点走，会在入口相遇
                // (x+y)*2 = x+y+n(y+z)   ---->>   x = (n-1)(y+z) + z
                ListNode dummyA = head;
                ListNode dummyB = slow;
                while (dummyA != dummyB){
                    dummyA = dummyA.next;
                    dummyB = dummyB.next;
                }

                return dummyA;

            }
        }
        return null;
    }
}
