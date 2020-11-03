package com.yufeng.leetcode421;

import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println(solution.findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
    }

    public int findMaximumXOR(int[] nums) {

        if (nums.length == 0) return 0;

        int max = 0;
        for(int num: nums) {
            max = Math.max(num, max);
        }

        String maxStr = Integer.toBinaryString(max);
        int len = maxStr.length();

        int mask = 1 << len;
        String[] arr = new String[nums.length];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.toBinaryString(mask | nums[i]).substring(1);
        }

        Stream.of(arr).forEach(System.out::println);

        root = new Node();
        for(String word: arr) {
            insert(word);
        }

        int result = 0, curr, bit;
        for(String bits: arr) {
            Node node = root;
            curr = 0; bit = (1 << (len - 1));
            for(char ch: bits.toCharArray()) {
                if ((ch == '1' && node.containsKey('0')) ||  (ch == '0' && node.containsKey('1'))) {
                    curr += bit;
                    node = ch == '1' ? node.get('0') : node.get('1');
                } else {
                    node = node.get(ch);
                }
                bit = bit >> 1;
            }
            result = Math.max(result, curr);
        }
        return result;
    }

    private void insert(String word) {
        Node node = root;
        for(char ch: word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new Node());
            }
            node = node.get(ch);
        }
        node.setEnd(true);
    }


    Node root;
    class Node {

        boolean isEnd;
        Node[] child;

        public Node() {
            this.isEnd = false;
            this.child = new Node[2];
        }

        public void put(char ch, Node node) {
            this.child[ch - '0'] = node;
        }

        public Node get(char ch) {
            return this.child[ch - '0'];
        }

        public boolean containsKey(char ch) {
            return this.child[ch - '0'] != null;
        }

        public void setEnd(boolean isEnd) {
            this.isEnd = isEnd;
        }

        public boolean isEnd() {
            return this.isEnd;
        }
    }
}