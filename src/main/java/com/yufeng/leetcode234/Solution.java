package com.yufeng.leetcode234;

import com.yufeng.leetcode.model.ListNode;

public class Solution {

    public static void main(String[] args) {

        Solution solution = new Solution();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(2);
//        ListNode node5 = new ListNode(1);
        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
        System.out.println(solution.isPalindrome(node1));
    }


    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) return true;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;
        ListNode prev = dummy, next = head;

        while (slow != null && fast != null && fast.next != null) {

            prev = slow;
            slow = next;
            fast = fast.next.next;
            next = slow.next;

            //reverse
            slow.next = prev;
            if (prev == dummy) {
                prev.next = null;
            }
        }

        head.next = null;
        ListNode head1 =  slow;
        ListNode head2 = next;
        if (fast == null) head1 = prev;

        while(head1 != null && head2 != null) {
            if (head1.val != head2.val) return false;
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1 == head2;
    }
}
