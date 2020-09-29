package com.yufeng.leetcode015;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int lo = i + 1;
            int hi = nums.length - 1;

            int sum;
            int curr;
            while (lo < hi) {
                sum = nums[i] + nums[lo] + nums[hi];
                if (sum == 0) {
                    List<Integer> ans = new LinkedList<>();
                    ans.add(nums[i]);
                    ans.add(nums[lo]);
                    ans.add(nums[hi]);
                    result.add(ans);


                    curr = nums[hi];
                    while(lo < hi && nums[hi] == curr) hi--;

                    curr = nums[lo];
                    while(lo < hi && nums[lo] == curr) lo++;
                } else if (sum > 0) {

                    curr = nums[hi];
                    while(lo < hi && nums[hi] == curr) hi--;
                } else {

                    curr = nums[lo];
                    while(lo < hi && nums[lo] == curr) lo++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
