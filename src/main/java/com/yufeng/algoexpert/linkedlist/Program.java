package com.yufeng.algoexpert.linkedlist;

import java.util.*;

// Feel free to add new properties and methods to the class.
class Program {
    static class DoublyLinkedList {
        public Node head;
        public Node tail;

        public void setHead(Node node) {
            // Write your code here.
            System.out.println("before setHead");
            printList(head);

            if (head == null) {
                head = node;
                tail = node;
                return;
            }

            Node curr = head;
            node.next = curr;
            curr.prev = node;
            head = node;

            // System.out.println(head);
            System.out.println("after setHead: " + head.value);
            printList(head);
        }

        public void setTail(Node node) {
            // Write your code here.
            if (tail == node) {
                head = node;
                tail = node;
                return;
            }

            Node curr = tail;
            curr.next = node;
            node.prev = curr;
            tail = node;
            System.out.println("setTail");
        }

        public void insertBefore(Node node, Node nodeToInsert) {
            // Write your code here.
            Node prev = null, curr = head, next = null;
            while(curr!= null) {
                next = curr.next;
                if (curr.value == nodeToInsert.value) break;
                prev = curr;
                curr = next;
            }

            if (prev == null) {
                setHead(node);
            } else {
                node.prev = prev;
                node.next = curr;
                curr.prev = node;
                prev.next = node;
            }

        }

        public void insertAfter(Node node, Node nodeToInsert) {
            // Write your code here.
            Node prev = null, curr = head, next = null;
            while(curr!= null) {
                next = curr.next;
                if (curr.value == nodeToInsert.value) break;
                prev = curr;
                curr = next;
            }

            if (curr.next == null) {
                setTail(node);
            } else {
                node.prev = curr;
                node.next = next;
                curr.next = node;
                next.prev = node;
            }

        }

        public void insertAtPosition(int position, Node nodeToInsert) {
            // Write your code here.
            Node curr = head, next = null;
            int len = 0;
            while(curr != null) {
                len++;
                if (len == position) break;
                curr = curr.next;
            }

            insertBefore(curr, nodeToInsert);
        }

        public void removeNodesWithValue(int value) {
            // Write your code here.
            Node prev = null, curr = head, next = null;
            while(curr != null) {
                next = curr.next;
                if (curr.value == value) {
                    if (prev == null) {
                        head = curr;
                    } else {
                        prev = next;
                    }


                    if (next != null) {
                        next.prev = prev;
                    } else {
                        tail = prev;
                        continue;
                    }

                    curr = next;
                    next = next.next;
                    if (next == null) tail = curr;
                    next.prev = curr;
                }

                prev = curr;
                curr = next;
            }

        }

        public void remove(Node node) {
            // Write your code here.

            Node prev = null, curr = head, next = null;
            while(curr != null) {
                next = curr.next;
                if (curr.value == node.value) {
                    curr = next;
                    if (prev == null) {
                        head = curr;
                    } else {
                        prev.next = curr;
                        curr.prev = prev;
                    }
                    break;
                }

            }

        }

        public boolean containsNodeWithValue(int value) {
            // Write your code here.
            Node prev = null,  curr = head, next = null;
            while(curr != null) {
                next = curr.next;
                if (curr.value == value) return true;

                prev = curr;
                curr = next;
            }
            return false;
        }
    }


    public static void printList(Node head) {

        Node curr = head;
        while(curr != null) {
            System.out.print(curr.value + " ");
            curr = curr.next;
        }
        System.out.println();
    }



    // Do not edit the class below.
    static class Node {
        public int value;
        public Node prev;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.setHead(new Node(5));
        doublyLinkedList.setHead(new Node(4));
        doublyLinkedList.setHead(new Node(3));
        doublyLinkedList.setHead(new Node(2));
        doublyLinkedList.setHead(new Node(1));
        doublyLinkedList.setHead(new Node(4));
        printList(doublyLinkedList.head);
    }
}

