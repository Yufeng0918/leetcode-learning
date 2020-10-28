package com.yufeng.leetcode428;

import com.yufeng.leetcode.model.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
        Node node1 = new Node(1);

        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        Node node13 = new Node(13);
        Node node14 = new Node(14);

        List<Node> l1 = new LinkedList<>();
        l1.add(node2);
        l1.add(node3);
        l1.add(node4);
        l1.add(node5);
        node1.children = l1;

        List<Node> l2 = new LinkedList<>();
        l2.add(node6);
        l2.add(node7);
        node3.children = l2;

        List<Node> l3 = new LinkedList<>();
        l3.add(node8);
        node4.children = l3;

        List<Node> l4 = new LinkedList<>();
        l4.add(node9);
        l4.add(node10);
        node5.children = l4;

        List<Node> l5 = new LinkedList<>();
        l5.add(node11);
        node7.children = l5;

        List<Node> l6 = new LinkedList<>();
        l6.add(node12);
        node8.children = l6;

        List<Node> l7 = new LinkedList<>();
        l7.add(node13);
        node9.children = l7;

        List<Node> l8 = new LinkedList<>();
        l8.add(node14);
        node11.children = l8;


        Solution solution = new Solution();
        String str =solution.serialize(node1);
        System.out.println(str);
        Node root = solution.deserialize(str);
        System.out.println(root);
    }

    // Encodes a tree to a single string.
    public String serialize(Node root) {

        if (root == null) return null;

        StringBuilder sb = new StringBuilder();
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        q.offer(null);
        int size;
        Node curr;
        while(!q.isEmpty()) {

            size = q.size();
            for(int i = 0; i < size; i++) {
                curr = q.poll();
                if (curr == null) {
                    sb.append("null,");
                } else {
                    sb.append(curr.val);
                    sb.append(",");
                    if (curr.children == null || curr.children.size() == 0) {
                        q.offer(null);
                        continue;
                    }

                    for(Node child: curr.children) {
                        q.offer(child);
                    }
                    q.offer(null);
                }
            }
//            sb.append("null,");
        }
        String ans = sb.toString();
        return ans.substring(0, ans.length() - 1);
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {

        if (data == null) return null;

        String[] strs = data.split(",");
        if (strs.length == 1 && strs[0].equals("")) return null;

        if (strs.length == 1 || strs.length == 2) return new Node(Integer.valueOf(strs[0]));

        Queue<Node> q = new LinkedList<>();
        Node curr, root = new Node(Integer.valueOf(strs[0]));
        root.children = new LinkedList<Node>();
        q.offer(root);
        int i = 2;
        while(i < strs.length) {
            int size = q.size();
            for(int j = 0; j < size; j++) {
                curr = q.poll();
                curr.children = new LinkedList<Node>();
                while(i < strs.length && !strs[i].equals("null")) {
                    Node child = new Node(Integer.valueOf(strs[i++]));
                    child.children = new LinkedList<>();
                    curr.children.add(child);
                    q.offer(child);
                }
                i++;
            }
        }
        return root;
    }
}
