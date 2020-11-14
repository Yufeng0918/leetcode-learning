package com.yufeng.leetcode131;

import java.util.LinkedList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.partition("cdd");
    }

    List<List<String>> ans = new LinkedList<>();

    public List<List<String>> partition(String s) {

        generate("", 0, s, new LinkedList<>());
        return ans;
    }

    public void generate(String prev, int idx, String s, LinkedList<String> list) {

        if (idx == s.length()) {
            if (isPalindrome(prev)) {
                ans.add(new LinkedList<>(list));
            }
            return;
        }

        String curr = s.substring(idx, idx + 1);
        String newStr = prev + curr;

        if (isPalindrome(prev)) {

            if (prev.equals("")) {
                list.addLast(curr);
                generate(curr, idx + 1, s, list);
                list.removeLast();
            } else {
                String temp = list.removeLast();
                list.addLast(newStr);
                generate(newStr, idx + 1, s, list);
                list.removeLast();
                list.addLast(temp);

                list.addLast(curr);
                generate(curr, idx + 1, s, list);
                list.removeLast();
            }
        } else {
            if (isPalindrome(newStr)) {
                String temp = list.removeLast();
                list.addLast(newStr);
                generate(newStr, idx + 1, s, list);
                list.removeLast();
                list.addLast(temp);
            } else {
                generate(newStr, idx + 1, s, list);
            }

        }
    }

    public boolean isPalindrome(String text) {

        if (text.length() == 0 || text.length() == 1) return true;

        int lo = 0, hi = text.length() - 1;
        while (lo <= hi) {
            if (text.charAt(lo++) != text.charAt(hi--)) return false;
        }
        return true;
    }
}