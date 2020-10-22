package com.yufeng.leetcode424;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.characterReplacement("AABABBA",1));
    }

    private int[] map = new int[26];
    
    public int characterReplacement(String s, int k) {
        if (s == null) return 0;

        char[] chars = s.toCharArray();
        int left = 0, right = 0, historyCharMax = 0;
        for (right = 0; right < chars.length; right++) {
            int index = chars[right] - 'A';
            map[index]++;
            historyCharMax = Math.max(historyCharMax, map[index]);
            if (right - left + 1 > historyCharMax + k) {
                map[chars[left] - 'A']--;
                left++;
            }
        }
        return chars.length - left;
    }
}
