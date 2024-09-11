package com.jd.leetcode;

import com.jd.leetcode.linkedList.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 双指针综合
 */
public class TwoPoint {
    /**
     * 移除元素
     */
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] != val) {
                nums[left++] = nums[right];
            }
            right++;
        }
        return left;
    }

    /**
     * 反转链表
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode fast = head;
        while (fast != null) {
            ListNode last = fast.getNext();
            fast.setNext(pre);
            pre = fast;
            fast = last;
        }
        return pre;
    }

    /**
     * 删除链表的倒数第N个结点
     * 一个节点先走n步，然后第二个开始走，第一个走成null了，第一个指着的就是倒数n
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode fast = dummy;
        for (int i = 0; i < n + 1; i++) {
            fast = fast.getNext();
        }
        ListNode slow = dummy;
        while (fast != null) {
            fast = fast.getNext();
            slow = slow.getNext();
        }
        slow.setNext(slow.getNext().getNext());
        return dummy.getNext();
    }

    /**
     * 链表相交
     * <p>
     * 1、计算长度，根据长度来先走相差值
     * 2、一个走完就让新节点去另一个的路开始走，相遇就是相交
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode preA = headA;
        ListNode preB = headB;
        while (preA != preB) {
            if (preA != null) {
                preA = preA.getNext();
            } else {
                preA = headB;
            }
            if (preB != null) {
                preB = preB.getNext();
            } else {
                preB = headA;
            }
        }
        return preA;
    }

    /**
     * 环形链表II
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) {
                ListNode dummyA = head;
                ListNode dummyB = slow;
                while (dummyA != dummyB) {
                    dummyA = dummyA.getNext();
                    dummyB = dummyB.getNext();
                }
                return dummyA;
            }
        }
        return null;
    }

    /**
     * 三数之和
     * <p>
     * 一个数从左到右循环，两个数左右可以移动
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] > 0) {
                break;
            }
            int mid = i + 1;
            int right = nums.length - 1;
            while (mid < right) {
                if (nums[i] + nums[mid] + nums[right] == 0) {
                    list.add(Arrays.asList(nums[i], nums[mid], nums[right]));
                    for (++mid; mid<right&&nums[mid]==nums[mid-1];mid++);
                    for (--right;mid<right&&nums[right]==nums[right+1];right++);
                }else if(nums[i] + nums[mid] + nums[right]<0){
                    mid++;
                }else {
                    right--;
                }
            }
        }
        return list;
    }


}
