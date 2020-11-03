package com.yufeng.leetcode336;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {



    public List<List<Integer>> palindromePairs(String[] words) {

        for(int i = 0; i < words.length; i++) {
            insert(words[i], i);
        }

        for(int i = 0; i < words.length; i++) {


        }

        return new LinkedList<>();
    }


    private List<List<Integer>> search(String[] words, String word, int idx) {

        Node node = root;
        int i = word.length() - 1;
        for(; i >= 0; i--) {
            if (node.isEnd()) break;
            char ch = word.charAt(i);
            if (node.containsKey(ch)) {
                node = node.get(ch);
            } else {
                return new LinkedList<>();
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        if (node.isEnd() && i == -1) {
            ans.add(Arrays.asList(node.getIdx(), idx));
        }

        int len;
        if (node.isEnd()) {
            int j = node.getIdx();
            len = words[j].length();
            if (isPalindrome(word.substring(len))) {
                ans.add(Arrays.asList(node.getIdx(), idx));
            }
        } else {

            List<Integer> wIdx = findWords(node);
            len = word.length();
            for (Integer q : wIdx) {
                String str = words[q];
                if (isPalindrome(str.substring(len))) {
                    ans.add(Arrays.asList(q, idx));
                }
            }
        }
        return ans;
    }

    private boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) return true;

        int i = 0, j = word.length() - 1;
        while(i <= j) {
            char c1 = word.charAt(i++);
            char c2 = word.charAt(j--);
            if (c1 != c2) return false;
        }
        return true;
    }

    private List<Integer> findWords(Node node) {

        List<Integer> wIdx = new ArrayList<>();
        for(Node next: node.child) {
            if (next != null) {
                wIdx.addAll(findWords(next));
            }
        }
        if (node.isEnd) wIdx.add(node.idx);
        return wIdx;
    }


    Node root;
    private void insert(String word, int idx) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new Node());
            }
            node = node.get(ch);
        }

        node.setEnd(true);
        node.setIdx(idx);
    }

    class Node {

        boolean isEnd;
        int idx;
        Node[] child;

        Node() {
            this.isEnd = false;
            child = new Node[26];
        }

        public void put(char ch, Node node) {
            child[ch - 'a'] = node;
        }

        public Node get(char ch) {
            return child[ch - 'a'];
        }

        public boolean containsKey(char ch) {
            return child[ch - 'a'] != null;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }
    }
}