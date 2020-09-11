package com.yufeng.leetcode051;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solveNQueens(4);

    }

    public List<List<String>> solveNQueens(int n) {

        Set<Integer> row = new HashSet<>();
        Set<Integer> col = new HashSet<>();
        Set<Integer> left = new HashSet<>();
        Set<Integer> right = new HashSet<>();


        List<List<String>> result = new ArrayList<>();
        dfs(row, col, left, right, new LinkedList<String>(), result, 0, n);
        System.out.println("ans: " + result.size());
        System.out.println(result.get(0).get(0));
        return result;
    }

    public void dfs(Set<Integer> row, Set<Integer> col, Set<Integer> left, Set<Integer> right, LinkedList<String> curr, List<List<String>> result, int level, int n) {

        if (level >= n) {
//            System.out.println("add curr: " + curr);
            LinkedList<String> ans = new LinkedList<>();
            ans.addAll(curr);
            result.add(ans);
            return;
        }

        for(int i = 0; i < n; i++) {

            int r = level;
            int c = i;
            int le = r + c;
            int ri = r - c;
            if (row.contains(r) || col.contains(c) || left.contains(le) || right.contains(ri)) {
                continue;
            }


            row.add(r);
            col.add(c);
            left.add(le);
            right.add(ri);
            char[] chArr = new char[n];
            for(int j = 0; j < n; j++) {
                if (j == i) {
                    chArr[j] = 'Q';
                } else {
                    chArr[j] = '.';
                }
            }
            curr.add(new String(chArr));
            System.out.println("level " + level + ": " + curr);
            dfs(row, col, left, right, curr, result, level + 1, n);
            curr.removeLast();

            row.remove(r);
            col.remove(c);
            left.remove(le);
            right.remove(ri);
        }
    }
}
