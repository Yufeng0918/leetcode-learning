package com.yufeng.leetcode001;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap();

        for(int i = 0; i < nums.length; i++) {

            if (map.containsKey(target-nums[i])) {

                int index = map.get(target-nums[i]);
                return i > index ? new int[]{index, i} : new int[] {i, index};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {

        Solution s = new Solution();
        s.twoSum(new int[] {2, 7, 11, 5}, 9);

        System.out.println(882 % 10);
    }
}
