package com.yufeng.leetcode425;


import java.util.LinkedList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.wordSquares(new String[]{"area","lead","wall","lady","ball"});
//        solution.wordSquares(new String[]{"abat","baba","atan","atal"});
        System.out.println(solution.ans);
    }


    List<List<String>> ans;
    Tire tire;
    public List<List<String>> wordSquares(String[] words) {

        ans = new LinkedList<>();
        tire = new Tire();
        for(int i = 0; i < words.length; i++) {
            tire.insert(words[i]);
        }

        LinkedList result = new LinkedList<>();
        for (String word : words) {
            result.add(word);
            generate(words[0].length(), 1, result);
            result.removeLast();
        }
        return ans;
    }

    public void generate(int size, int idx, LinkedList<String> result) {

        if (idx >= size) {
            if (isValid(size, result)) {
                ans.add(new LinkedList<>(result));
            }
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < idx; i++) {
            sb.append(result.get(i).charAt(idx));
        }
        // char ch = word.charAt(idx);
        List<String> words = tire.search(sb.toString());
        for (String str : words) {
            tire.setUsed(str, true);
            result.addLast(str);
            generate(size, idx + 1, result);
            result.removeLast();
            tire.setUsed(str, false);
        }
    }

    public boolean isValid(int size, LinkedList<String> result) {

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if (result.get(j).charAt(i) != result.get(i).charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}

class Tire {

    Node root;
    public Tire() {
        root = new Node();
    }

    public void insert(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {

            if (!node.containsKey(ch)) node.put(ch, new Node());
            node = node.get(ch);
        }
        node.isEnd = true;
    }

    public void setUsed(String word, boolean used) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            node = node.get(ch);
        }
        node.isUsed = used;
    }


    public List<String> search(String prefix) {

        Node node = root;
        List<String> strs = new LinkedList<>();
        for (char ch : prefix.toCharArray()) {
            if (node.get(ch) == null) return strs;
            node = node.get(ch);
        }

        findAllString(node, prefix, strs);
        return strs;
    }

    public void findAllString(Node node, String prev, List<String> list) {
        if (node.isEnd) {
            list.add(prev);
            return;
        }

        char ch;
        for (int i = 0; i < node.child.length; i++) {
            ch = (char)('a' + i);
            if (node.get(ch) != null) {
                findAllString(node.get(ch), prev + ch, list);
            }
        }
    }
}


class  Node {

    boolean isEnd;

    boolean isUsed;

    Node[] child;

    public Node() {
        child = new Node[26];
    }

    public boolean containsKey(char ch) {
        return child[ch - 'a'] != null;
    }

    public void put(char ch, Node node) {
        child[ch - 'a'] = node;
    }

    public Node get(char ch) {
        return child[ch - 'a'];
    }
}