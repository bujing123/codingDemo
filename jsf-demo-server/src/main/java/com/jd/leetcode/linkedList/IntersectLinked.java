package com.jd.leetcode.linkedList;

/**
 * 面试题 02.07. 链表相交
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 *
 * 题目数据 保证 整个链式结构中不存在环。
 *
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 */
public class IntersectLinked {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /**先求得两个链表长度
        根据长度差值，长链表移动差值位数，然后一起移动，如果移动到相同next节点则说明有共同节点*/
        int lenA = 0;
        ListNode dummyA = headA;
        while (dummyA != null){
            lenA++;
            dummyA = dummyA.next;
        }
        int lenB = 0;
        ListNode dummyB = headB;
        while (dummyB != null){
            lenB++;
            dummyB = dummyB.next;
        }
        int diff = Math.abs(lenA-lenB);
        ListNode nodeA = headA;
        ListNode nodeB  =headB;
        if(lenA > lenB){
            while(diff-- > 0){
                nodeA = nodeA.next;
            }
        }else {
            while(diff-- > 0){
                nodeB = nodeB.next;
            }
        }
        while(nodeA != nodeB && nodeA != null && nodeB != null){
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }

        if(nodeA != nodeB){
            return null;
        }

        return nodeA;


    }

    /**
     * 还有一种上方的巧妙办法，一个走到末尾以后，就直接去走到另一个节点的开头，这样子双指针分别走到末尾的差值就是两个链表的差值
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB){
        if(headA ==null || headB == null){
            return null;
        }
        ListNode dummyA = headA;
        ListNode dummyB = headB;
        while(dummyA != dummyB){
            dummyA = dummyA == null? headB:dummyA.next;
            dummyB = dummyB == null? headA: dummyB.next;
        }
        return dummyA;
    }

}
