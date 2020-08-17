package com.yufeng.leetcode003;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int lengthOfLongestSubstring(String s) {

        int length = 0;
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0;

        while (i < s.length() && j < s.length()) {

            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
                length = Math.max(length, j - i);
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }

        return length;
    }

    public static void main(String[] args) {

        Solution s = new Solution();
        System.out.println(s.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(s.lengthOfLongestSubstring("bbbbb"));
        System.out.println(s.lengthOfLongestSubstring("au"));


    }
}
