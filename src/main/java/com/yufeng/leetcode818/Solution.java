package com.yufeng.leetcode818;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.racecar(4));
    }

    public int racecar(int target) {

        int[] dp = new int[target + 3];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 4;

        for(int i = 3; i < dp.length; i++) {
            int k = 32 - Integer.numberOfLeadingZeros(i);
            int forward = (1 << k) - 1;
            if (i == forward) {
                dp[i] = k;
                continue;
            }

            int steps = k + 1;
            dp[i] = Math.min(dp[i], dp[forward - i] + steps);
            int backward;
            for(int j = 0; j < k - 1; j++) {
                forward = (1 << (k - 1)) - 1;
                backward = (1 << j) - 1;
                steps = k - 1 + j + 2;
                dp[i] = Math.min(dp[i], dp[i - forward + backward] + steps);
            }
        }

        return dp[target];
    }
}
