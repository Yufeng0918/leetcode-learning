package com.yufeng.leetcode6099;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.longestSubsequence("001010101011010100010101101010010",
                93951055);
    }

    public int longestSubsequence(String s, int k) {


        char[] arr = s.toCharArray();
        int[][] dp = new int[arr.length][2];
        dp[0][0] = k >= arr[0] - '0' ? 1: 0;
        dp[0][1] = arr[0] - '0';


        int len = dp[0][0];
        for(int i = 1; i < dp.length; i++) {

            dp[i][0] = 0;
            dp[i][1] = k + 1;
            System.out.println("start from " + i);
            for(int j = 0; j < i; j++) {

                if (dp[j][0] == 0) continue;

                int n =  dp[j][1] * 2 + (arr[i] - '0');
                System.out.println("n: " + n);
                if (n <= k) {
                    if (dp[j][0] + 1 >= dp[i][0]) {
                        dp[i][0] = dp[j][0] + 1;
                        if (dp[j][0] + 1 == dp[i][0]) dp[i][1] = Math.min(dp[i][1], n);
                        else dp[i][1] = n;
                    }
                }
            }

            len = Math.max(len, dp[i][0]);
            System.out.println(i + ", " + len + ", " + dp[i][1]);
        }
        return len;
    }
}
