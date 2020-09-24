package com.yufeng.leetcode032;
class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestValidParentheses("(()))())("));
    }

    public int longestValidParentheses(String s) {

        int len = s.length();
        if (len == 0) return 0;

        int[] dp = new int[len];
        dp[0] = 0;

        for(int i = 1; i < len; i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0;
                continue;
            }


            int total = 0;
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    total += 2;
                } else {
                    total += dp[i - 1];
                    if ((i - dp[i - 1] - 1) >= 0) {
                        char c = s.charAt(i - dp[i - 1] - 1);
                        if (c == '(') {
                            total += 2;
                        } else {
                            dp[i] = 0;
                            continue;
                        }
                    } else {
                        dp[i] = 0;
                        continue;
                    }
                }
            }
            int j = i - total;
            if (j >= 0) {
                total += dp[j];
            }
            dp[i] = total;
        }

        int ans = 0;
        for(int j = 0; j < len; j++) {
            ans = Math.max(dp[j], ans);
        }

        return ans;
    }
}
