package com.yufeng.leetcode414;

import java.util.HashMap;
import java.util.Map;

public class Solution {

//    public int thirdMax(int[] nums) {
//
//        Integer num1 = Integer.MIN_VALUE;
//        Integer num2 = Integer.MIN_VALUE;
//        Integer num3 = Integer.MIN_VALUE;
//
//        boolean found = false;
//        for(int i = 0; i < nums.length; i++) {
//            if (nums[i] > num1) {
//                num3 = num2;
//                num2 = num1;
//                num1 = nums[i];
//            } else if (nums[i] > num2) {
//                if (num1 != nums[i]) {
//                    num3 = num2;
//                    num2 = nums[i];
//                    found = true;
//                }
//            } else if (nums[i] >= num3){
//                if (num2 != nums[i]) {
//                    num3 = nums[i];
//                    found = true;
//                }
//            }
//        }
//
//        return found ? num3 : num1;
//    }

    public int thirdMax(int[] nums) {

        if (nums.length == 1) return nums[0];

        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        Integer first = findMaxLess(nums, null);
        Integer second = findMaxLess(nums, first);
        if (second == null) return first;

        Integer third = findMaxLess(nums, second);
        if (third == null) return first;

        return third;
    }

    public Integer findMaxLess(int[] nums, Integer target) {

        Integer ans = null;
        for (int i = 0; i < nums.length; i++) {

            if (ans == null) {
                if (target == null || (target != null && nums[i] < target)) {
                    ans = nums[i];
                    continue;
                }
            }

            if (ans != null && nums[i] > ans) {
                if ((target != null && nums[i] < target) || (target == null)) {
                    ans = nums[i];
                }
            }
        }
        return ans;
    }



    public static void main(String[] args) {

        Solution s = new Solution();
//        System.out.println(s.thirdMax(new int[]{3, 2, 1}));


        String str1 = "abeefec";
        String str2 = "xyeefe";


        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();

        int [][] dp = new int[arr2.length + 1][arr1.length + 1];
        int ans = 0;
        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[i].length; j++) {
                if (arr1[j - 1] == arr2[i - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }

        System.out.println(ans);
    }
}
