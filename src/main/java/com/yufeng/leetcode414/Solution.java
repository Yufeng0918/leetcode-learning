package com.yufeng.leetcode414;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int thirdMax(int[] nums) {

        Integer num1 = Integer.MIN_VALUE;
        Integer num2 = Integer.MIN_VALUE;
        Integer num3 = Integer.MIN_VALUE;

        boolean found = false;
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] > num1) {
                num3 = num2;
                num2 = num1;
                num1 = nums[i];
            } else if (nums[i] > num2) {
                if (num1 != nums[i]) {
                    num3 = num2;
                    num2 = nums[i];
                    found = true;
                }
            } else if (nums[i] >= num3){
                if (num2 != nums[i]) {
                    num3 = nums[i];
                    found = true;
                }
            }
        }

        return found ? num3 : num1;
    }

    public static void main(String[] args) {

        Solution s = new Solution();
        System.out.println(s.thirdMax(new int[]{1,2,2,5,3,5}));
    }
}
