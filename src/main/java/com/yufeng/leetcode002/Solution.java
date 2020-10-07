package com.yufeng.leetcode002;


import com.yufeng.leetcode.model.ListNode;

public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(), node = dummy;

        int bit = 0, sum, val;
        while(l1 != null && l2 != null) {
            sum = l1.val + l2.val + bit;
            val = sum % 10;
            bit = sum / 10;
            node.next = new ListNode(val);
            node = node.next;
            l1 = l1.next;
            l2 = l2.next;
        }

         while(l1 != null) {
             sum = l1.val + bit;
             val = sum % 10;
             bit = sum / 10;
             node.next = new ListNode(val);
             node = node.next;
             l1 = l1.next;
         }

         while(l2 != null) {
             sum = l2.val + bit;
             val = sum % 10;
             bit = sum / 10;
             node.next = new ListNode(val);
             node = node.next;
             l2 = l2.next;
         }

        if (bit == 1) node.next = new ListNode(1);
        return dummy.next;
    }

    public static void main(String[] args) {

        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(9);
        ListNode l3 = new ListNode(9);
        ListNode l4 = new ListNode(9);
        ListNode l5 = new ListNode(9);
        ListNode l6 = new ListNode(9);
        ListNode l7 = new ListNode(9);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;


        ListNode n1 = new ListNode(9);
        ListNode n2 = new ListNode(9);
        ListNode n3 = new ListNode(9);
        ListNode n4 = new ListNode(9);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        Solution solution = new Solution();
        System.out.println(solution.addTwoNumbers(l1, n1));

    }

}
