package com.yufeng.leetcode239;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{-7,-8,7,5,7,1,6,0};
        int[] ans = solution.maxSlidingWindow(nums, 4);
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {

        if (nums.length < k - 1) return new int[]{};

        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < nums.length; i++) {

            if (i >= k && q.peek() == nums[i - k]) {
                q.removeFirst();
            }

            while(q.size() != 0 && q.getLast() < nums[i]) {
                q.removeLast();
            }
            q.addLast(nums[i]);
            if (i >= k - 1) {
                ans[i - k + 1] = q.getFirst();
            }
        }
        return ans;
    }
}
