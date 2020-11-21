package com.yufeng.leetcode637;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
        System.out.println(solution.findNumberOfLIS(new int[]{2, 2, 2, 2, 2}));
//        System.out.println(solution.findNumberOfLIS(new int[]{1,2,4,3,5,4,7,2}));
    }

    public int findNumberOfLIS(int[] nums) {

        if (nums.length <= 1) return nums.length;

        int[] len = new int[nums.length];
        int[] count = new int[nums.length];
        len[0] = 1;
        count[0] = 1;

        int result = 1;
        for(int i = 1; i < len.length; i++) {
            for(int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (len[j]  >= len[i]) {
                        len[i] = len[j] + 1;
                        count[i] = count[j];
                    } else if ((len[j] + 1) == len[i]) {
                        count[i] += count[j];
                    }
                }
            }
            if (len[i] == 0) {
                len[i] = 1;
                count[i] = 1;
            }
            result = Math.max(len[i], result);
        }

        int c = 0;
        for(int i = 0; i < count.length; i++) {
            if (len[i] == result) c += count[i];
        }

        return c;
    }
}