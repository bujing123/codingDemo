package com.jd.leetcode.linkedList;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode getNext(){
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
