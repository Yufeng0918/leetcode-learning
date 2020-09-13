package com.yufeng.leetcode127;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> list = new LinkedList<>();
        list.add("hot");
        list.add("dot");
        list.add("tog");
//        list.add("dog");
//        list.add("lot");
//        list.add("log");
        list.add("cog");
        System.out.println(solution.ladderLength("hit", "cog", list));

    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> start = new HashSet<>();
        start.add(beginWord);

        Set<String> end = new HashSet<>();
        end.add(endWord);

        Set<String> wordSet = new HashSet<>();
        wordSet.addAll(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        visited.add(endWord);

        int step = 1;
        while(start.size() != 0 && end.size() != 0) {

            Set<String> temp = new HashSet<>();
            for(String str: start) {
                for(String e: end) {
                    if(oneCharDiff(str, e)) return step + 1;
                }
                generateNextSet(str, temp, wordSet, visited);
            }

            step++;
            start = temp;
            if (start.size() > end.size()) {
                temp = start;
                start = end;
                end = temp;
            }
             System.out.println("start: " + start.size() + " end: " + end.size());
        }
        return 0;
    }

    public boolean oneCharDiff(String str1, String str2) {
        int diff = 0;
        for(int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                diff++;
            }

            if (diff > 1) return false;
        }
        // System.out.println(str1 + " " + str2 +  " diff: " + diff);
        return diff == 1;
    }

    public void generateNextSet(String str, Set<String> temp, Set<String> wordSet, Set<String> visited) {

        for (int i = 0; i < str.length(); i++) {
            char[] charArr = str.toCharArray();
            for(int j = 0; j < 26; j++) {
                charArr[i] = (char)('a' + j);

                String next = new String(charArr);
                if (next.equals(str)) continue;

                if (wordSet.contains(next) && !visited.contains(next)) {
                    temp.add(next);
                    visited.add(next);
                }
            }
        }
    }
}
