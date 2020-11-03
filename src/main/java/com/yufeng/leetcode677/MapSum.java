package com.yufeng.leetcode677;

public class MapSum {

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));           // return 3 (apple = 3)
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));
    }

    /** Initialize your data structure here. */
    Node root;
    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {

        Node node = root;
        for(char ch: key.toCharArray()) {
            if (!node.containsKey(ch)) {
                Node child = new Node();
                node.put(ch, child);
            }
            node = node.get(ch);
        }
        node.setEnd(true);
        node.setNum(val);
    }

    public int sum(String prefix) {
        Node node = root;
        for(char ch: prefix.toCharArray()) {
            if (!node.containsKey(ch)) {
                return 0;
            }
            node = node.get(ch);
        }
        return getSum(node);
    }

    public int getSum(Node node) {
        int sum = 0;
        if (node.isEnd()) sum += node.getNum();

        for(Node child: node.getChild()) {
            if (child != null) sum += getSum(child);
        }
        return sum;
    }


    class Node {

        Node[] child;

        int num;

        boolean isEnd;

        public Node() {
            this.child = new Node[26];
            this.num = 0;
            this.isEnd = false;
        }


        public boolean containsKey(char ch) {
            return child[ch - 'a'] != null;
        }

        public Node get(char ch) {
            return child[ch - 'a'];
        }

        public void put(char ch, Node node) {
            child[ch - 'a'] = node;
        }

        public void setEnd(boolean isEnd) {
            this.isEnd = isEnd;
        }

        public boolean isEnd() {
            return this.isEnd;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getNum() {
            return this.num;
        }

        public Node[] getChild() {
            return this.child;
        }
    }
}

