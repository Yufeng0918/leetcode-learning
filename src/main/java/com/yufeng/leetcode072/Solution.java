package com.yufeng.leetcode072;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minDistance("horse", "ros"));
    }

    public int minDistance(String word1, String word2) {

        word1 = " " + word1;
        word2 = " " + word2;

        char[] chArr1 = word1.toCharArray();
        char[] chArr2 = word2.toCharArray();

        int[][] dp = new int[chArr2.length][chArr1.length];

        for(int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }

        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }

        for(int i = 1; i < chArr2.length; i++) {
            for(int j = 1; j < chArr1.length; j++) {
                if (chArr2[i] == chArr1[j]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1);
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                }
            }
        }

        return dp[chArr2.length - 1][chArr1.length - 1];
    }
}
