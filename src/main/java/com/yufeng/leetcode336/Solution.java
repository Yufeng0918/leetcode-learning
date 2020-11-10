package com.yufeng.leetcode336;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {


    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.palindromePairs(new String[]{"a",""}));
//        System.out.println(solution.palindromePairs(new String[]{"abcd","dcba","lls","s","sssll", ""}));
//        System.out.println(solution.palindromePairs(new String[]{"abababb","ccaacab","ccbcbbb","","bbc","cca","abcbbba","bcccaac","bab","caacca"}));

        List<List<Integer>> a = new LinkedList<>();
        List<Integer> l1 = new LinkedList<>();
        l1.add(1);
        l1.add(2);
        a.add(l1);
        l1.remove(1);
        System.out.println(a.get(0).size());
    }


    public List<List<Integer>> palindromePairs(String[] words) {

        root = new Node();
        for(int i = 0; i < words.length; i++) {
            insert(words[i], i);
        }

        List<List<Integer>> ans = new LinkedList<>();
        for(int i = 0; i < words.length; i++) {
            ans.addAll(search(words, words[i], i));
        }
        return ans;
    }


    private List<List<Integer>> search(String[] words, String word, int idx) {

        Node node = root;
        int i = word.length() - 1;
        List<List<Integer>> ans = new LinkedList<>();
        for(; i >= 0; i--) {
            if (node.isEnd) {
                ans.addAll(getListIfNodeEnd(words, word, idx, node));
            }
            char ch = word.charAt(i);
            if (node.containsKey(ch)) {
                node = node.get(ch);
            } else {
                break;
            }
        }

        if (i == -1 ) {
            if (node.isEnd) {
                ans.addAll(getListIfNodeEnd(words, word, idx, node));
                ans.addAll(getListsIfPrefixNotEnd(words, word, idx, node));
            } else {
                ans.addAll(getListsIfPrefixNotEnd(words, word, idx, node));
            }
        }

        return ans;
    }

    private List<List<Integer>> getListIfNodeEnd(String[] words, String word, int idx, Node node) {
        List<List<Integer>> ans = new ArrayList<>();
        String prefix = words[node.getIdx()];
        int len = prefix.length();
        if (node.getIdx() != idx && isPalindrome(word.substring(0, word.length() - len))) {
            ans.add(Arrays.asList(node.getIdx(), idx));
        }
        return ans;
    }


    private List<List<Integer>> getListsIfPrefixNotEnd(String[] words, String word, int idx, Node node) {
        List<List<Integer>> ans = new ArrayList<>();
        int len;
        List<Integer> wIdx = findWords(node);
        len = word.length();
        for (Integer q : wIdx) {
            String str = words[q];
            if (len < str.length() && isPalindrome(str.substring(len)) && q != idx) {
                ans.add(Arrays.asList(q, idx));
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