package com.yufeng.leetcode717;

import com.yufeng.leetcode.model.ListNode;

public class Solution {

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(7);
        linkedList.addAtHead(2);
        linkedList.addAtHead(1);
        linkedList.addAtIndex(3,0);
        linkedList.deleteAtIndex(2);
        linkedList.addAtHead(6);
        linkedList.addAtTail(4);
        System.out.println(linkedList.get(4));
        linkedList.addAtHead(4);
        linkedList.addAtIndex(5, 0);
        linkedList.addAtHead(6);
    }
}


class MyLinkedList {

    private ListNode head;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = null;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {

        if (index < 0 || head == null) return -1;

        int i = 0;
        ListNode curr = head;
        while(curr != null) {
            if (i == index) return curr.val;
            curr = curr.next;
            i++;
        }

        return -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {

        ListNode node = new ListNode(val);
        ListNode next = head;
        node.next = next;
        head = node;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {

        ListNode node = new ListNode(val);
        if (head == null) {
            head = node;
            return;
        }

        ListNode curr = head;
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = node;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {

        if (index < 0) return;

        if (index == 0) {
            addAtHead(val);
            return;
        }

        ListNode node = new ListNode(val);
        int i = 0;
        ListNode curr = head;
        while(i + 1 < index && curr != null) {
            curr = curr.next;
            i++;
        }
        if (i + 1 == index) {
            ListNode next = curr.next;
            node.next = next;
            curr.next = node;
        }

    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0) return;

        if (head == null) return;

        if (index == 0) {
            head = head.next;
            return;
        }

        int i = 0;
        ListNode curr = head;
        while(i + 1 < index && curr != null) {
            curr = curr.next;
            i++;
        }
        if (i + 1 == index && curr.next != null) {
            ListNode next = curr.next.next;
            curr.next = next;
        }
    }
}