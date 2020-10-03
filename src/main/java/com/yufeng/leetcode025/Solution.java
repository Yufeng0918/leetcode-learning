package com.yufeng.leetcode025;

import com.yufeng.leetcode.model.ListNode;

import java.util.List;

public class Solution {

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        Solution solution = new Solution();
        ListNode ans = solution.reverseKGroup(node1, 2);
        System.out.println(ans);
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || head.next == null) return head;

        int i = 1;
        ListNode node = head, curr = node;
        while (node != null && i < k) {
            node = node.next;
            i++;
        }
        if (i != k) return node;

        ListNode last = node;
        ListNode next = last.next;

        node = curr;
        ListNode subList = reverseKGroup(next, k);
        last.next = null;

        ListNode currHead = reverseSubList(node);
        curr.next = subList;

        return currHead;
    }

    public ListNode reverseSubList(ListNode head) {

        if (head == null || head.next == null) return head;

        ListNode next = head.next;
        ListNode newHead = reverseSubList(next);
        next.next = head;
        head.next = null;
        return newHead;
    }


//    public ListNode reverseKGroup(ListNode head, int k) {
//
//        if (head == null || head.next == null) return head;
//
//        ListNode dummyHead = new ListNode(0);
//        dummyHead.next = head;
//
//        ListNode curr = head, node = curr, prev = dummyHead, last = null, next = null;
//        while(curr != null) {
//
//            // get last
//            int i = 0;
//            node = curr;
//            while(node != null && i < k - 1) {
//                node = node.next;
//                i++;
//            }
//            if (node == null) break;
//            last = node;
//            // get next
//            next = last.next;
//
//            // reverse
//            last.next = null;
//            node = curr;
//            reverseList(node);
//            prev.next = last;
//            curr.next = next;
//
//            // move
//            prev = curr;
//            curr = next;
//        }
//
//        return dummyHead.next;
//    }

    public void reverseList(ListNode head) {

        if (head == null || head.next == null) return;

        ListNode prev = null, next = null;
        while (head != null) {
            // get next
            next = head.next;

            // repoint
            head.next = prev;

            prev = head;
            head = next;
        }
    }
}
