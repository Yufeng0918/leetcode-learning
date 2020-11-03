package com.yufeng.leetcode211;

public class WordDictionary {


    public static void main(String[] args) {

        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("b.."));
        System.out.println();
    }


    /** Initialize your data structure here. */
    Node root;
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node node = root, next;
        for(char ch: word.toCharArray()) {
            if (!node.containsKey(ch)) {
                next = new Node();
                node.put(ch, next);
            }
            node = node.get(ch);
        }
        node.setEnd(true);
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {

        return search(root, word, 0);
    }

    public boolean search(Node node, String word, int i) {
        if (i == word.length()) {
            if (node.isEnd()) return true;
            return false;
        }

        char ch = word.charAt(i);
        if (ch != '.' ) {
            if (!node.containsKey(ch)) {
                return false;
            } else {
                return search(node.get(ch), word, i + 1);
            }
        } else {
            for(Node next: node.getChild()) {
                if (next != null) {
                    boolean result = search(next, word, i + 1);
                    if (result) return true;
                }
            }
            return false;
        }
    }

    class Node {

        Node[] child;
        boolean isEnd;

        public Node() {
            child = new Node[128];
            this.isEnd = false;
        }


        public void put(char ch, Node node) {
            child[ch - '.'] = node;
        }

        public Node get(char ch) {
            return child[ch - '.'];
        }

        public boolean containsKey(char ch) {
            return child[ch - '.'] != null;
        }

        public void setEnd(boolean isEnd) {
            this.isEnd = isEnd;
        }

        public boolean isEnd() {
            return this.isEnd;
        }

        public Node[] getChild() {
            return this.child;
        }
    }
}