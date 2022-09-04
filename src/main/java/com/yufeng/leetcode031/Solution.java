package com.yufeng.leetcode031;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    List<List<Integer>> ans;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        ans = new ArrayList<>();
        dfs(0, candidates, target, 0, new ArrayList<Integer>());
        return ans;
    }

    public void dfs(int idx, int[] candidates, int target, int total, List<Integer> prev) {

        if (total == target) {
            List<Integer> list = new ArrayList<>();
            for(Integer n: prev) {
                list.add(n);
                System.out.print(n + " ");
            }
            System.out.println();
            System.out.println("add to ans");
            ans.add(list);
            return;
        }

        if (total > target) return;

        if (idx == candidates.length) return;

        int nextTotal = total;
        while (nextTotal <= target) {
            dfs(idx + 1, candidates, target, nextTotal, prev);
            List<Integer> curr = new ArrayList<>(prev);
            curr.add(candidates[idx]);
            nextTotal += candidates[idx];
            System.out.println(idx + " next total: " + nextTotal);
            dfs(idx + 1, candidates, target, nextTotal, curr);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.combinationSum(new int[]{2, 2, 3, 7}, 7);
    }
}
