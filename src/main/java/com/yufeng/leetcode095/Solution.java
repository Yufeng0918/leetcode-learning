package com.yufeng.leetcode095;

import com.yufeng.leetcode.model.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<TreeNode> ans = solution.generateTrees(3);
        System.out.println(ans);
    }

    public List<TreeNode> generateTrees(int n) {

        int[] arr = new int[n];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        List<TreeNode> result = new LinkedList<>();
        for(int i = 0; i < arr.length; i++) {
            result.addAll(build(arr, i, 0, arr.length - 1));
        }
        return result;
    }

    public List<TreeNode> build(int[] arr, int m, int l, int r) {
        if (l > r) return new LinkedList<>();

        List<TreeNode> ans = new LinkedList<>();
        if (l == r) {
            ans.add(new TreeNode(arr[m]));
            return ans;
        }

        List<TreeNode> left = new LinkedList<>();
        for(int i = l; i <= m - 1; i++) {
            left.addAll(build(arr, i, l, m - 1));
        }

        List<TreeNode> right = new LinkedList<>();
        for(int i = m + 1; i <= r; i++) {
            right.addAll(build(arr, i, m + 1, r));
        }

        if (left == null || left.size() == 0) {
            for(TreeNode rn: right) {
                ans.add(new TreeNode(arr[m], null, rn));
            }
        } else if (right == null || right.size() == 0) {
            for(TreeNode ln: left) {
                ans.add(new TreeNode(arr[m], ln, null));
            }
        } else {
            for(TreeNode ln: left) {
                for(TreeNode rn: right) {
                    TreeNode curr = new TreeNode(arr[m], ln, rn);
                    ans.add(curr);
                }
            }
        }

        return ans;
    }
}
