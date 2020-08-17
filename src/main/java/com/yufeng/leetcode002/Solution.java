package com.yufeng.leetcode002;


public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = null;
        ListNode previousNode = null;
        boolean advancePlusOne = false;

        while (l1 != null || l2 != null || advancePlusOne) {

            int left;
            if (l1 == null) {
                left = 0;
            } else {
                left = l1.val;
            }


            int right;
            if (l2 == null) {
                right = 0;
            } else {
                right = l2.val;
            }


            int sum = right + left;
            if (advancePlusOne){
                sum++;
            }
            int mod = sum % 10;


            ListNode node = new ListNode(mod);
            if (previousNode == null) {
                result = node;
            } else {
                previousNode.next = node;
            }


            if (sum  >= 10) {
                advancePlusOne = true;
            } else {
                advancePlusOne = false;
            }


            previousNode = node;
            if (l1 == null) {
                l1 = null;
            } else {
                l1 = l1.next;
            }

            if (l2 == null) {
                l2 = null;
            } else {
                l2 = l2.next;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        ListNode lhead = new ListNode(2);
        ListNode lnode1 = new ListNode(4);
        ListNode lnode2 = new ListNode(3);
        lhead.next = lnode1;
        lnode1.next = lnode2;
        lnode2.next = null;


        ListNode rhead = new ListNode(5);
        ListNode rnode1 = new ListNode(6);
        ListNode rnode2 = new ListNode(4);
        rhead.next = rnode1;
        rnode1.next = rnode2;
        rnode2.next = null;

        Solution solution = new Solution();
        System.out.println(solution.addTwoNumbers(lhead, rhead));

    }

}
