package com.yufeng.leetcode042;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int trap(int[] height) {

        if (height.length == 0 || height.length == 1) return 0;

        int area = 0, h, w;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < height.length; i++) {
            if (stack.size() == 0 || height[i] <= height[stack.peek()]) {
                stack.push(i);
                continue;
            }

            while(stack.size() != 0 && height[i] > height[stack.peek()]) {
                h = height[stack.pop()];
                if (stack.size() == 0) continue;
                h = Math.min(height[stack.peek()], height[i]) - h;
                w = i - stack.peek() - 1;
                area = area + h * w;
            }
            stack.push(i);
        }
        return area;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}