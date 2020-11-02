package com.yufeng.leetcode642;

import java.util.*;
import java.util.stream.Collectors;

public class AutocompleteSystem {

    public static void main(String[] args) {

        AutocompleteSystem system = new AutocompleteSystem(new String[]{"i love you", "island","ironman", "i love leetcode"}, new int[]{5,3,2,2});
        List<String> result = new LinkedList<>();
        result = system.input('i');
        result = system.input(' ');
        result = system.input('a');
        result = system.input('#');

        result = system.input('i');
        result = system.input(' ');
        result = system.input('a');
        result = system.input('#');
        System.out.println(result);
    }


    StringBuilder sb;
    Node curr;
    public AutocompleteSystem(String[] sentences, int[] times) {

        root = new Node();
        sb = new StringBuilder();

        for(int i = 0; i < sentences.length; i++) {
            for(int j = 0; j < times[i]; j++) {
                insert(sentences[i]);
            }
        }
        curr = root;
    }

    public List<String> input(char c) {
        if (c != '#') {
            sb.append(c);
        }

        List<String> ans = search(sb.toString());
        if (c == '#') {
            insert(sb.toString());
            sb = new StringBuilder();
            curr = root;
        }
        return ans;
    }

    Node root;
    public void insert(String word) {

        Node node = root, next;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                next = new Node();
                node.put(ch, next);
            }
            node = node.get(ch);
        }
        node.setEnd(true);
        node.setTimes(node.getTimes() + 1);
    }

    public List<String> search(String str) {

        Node node = root;
        for(char ch: str.toCharArray()) {
            if (node.containsKey(ch)) {
                node = node.get(ch);
            } else {
                return new LinkedList<>();
            }
        }

        List<Input> ans = findList(node, str);
        Collections.sort(ans, (i1, i2) -> {
            if (i1.getTimes() != i2.getTimes()) return i2.getTimes() - i1.getTimes();
            return i1.getWord().compareTo(i2.getWord());
        });
        return ans.stream().map(i -> i.getWord()).limit(3).collect(Collectors.toList());
    }

    public List<Input> findList(Node curr, String str) {

        List<Input> ans = new ArrayList<>();
        if (curr.isEnd()) {
            ans.add(new Input(str, curr.getTimes()));
        }

        for(int i = 0; i < 128; i++) {
            if (curr.getChild()[i] != null) {
                String newstr = str + ((char)(' ' + i));
                ans.addAll(findList(curr.getChild()[i], newstr));
            }
        }
        return ans;
    }


    class Input {

        private String word;

        private int times;

        public Input(String word, int times) {
            this.word = word;
            this.times = times;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }
    }

    class Node {

        private boolean isEnd;

        private Node[] child;

        private int times;

        public Node() {
            this.isEnd = false;
            this.child = new Node[128];
        }

        public Node get(char ch) {
            return child[ch - ' '];
        }

        public void put(char ch, Node node) {
            child[ch - ' '] = node;
        }

        public boolean containsKey(char ch) {
            return child[ch - ' '] != null;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }

        public Node[] getChild() {
            return child;
        }

        public void setChild(Node[] child) {
            this.child = child;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }
    }
}
