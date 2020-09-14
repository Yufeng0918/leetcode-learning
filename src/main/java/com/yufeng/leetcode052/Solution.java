package com.yufeng.leetcode052;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.size = 4;
        solution.count = 0;
        System.out.println(solution.totalNQueues(4));
    }


    private int size;
    private int count;

    private void solve(int row, int ld, int rd) {
        if (row == size) {
            count++;
            return;
        }

        int pos = size & (~(row | ld | rd));
        while (pos != 0) {
            int p = pos & (-pos);
            pos -= p;
            solve(row | p, (ld | p) << 1, (rd | p) >> 1);
        }
    }


    public int totalNQueues(int n) {
        count = 0;
        size = (1 << n) - 1;
        solve(0, 0, 0);
        return count;
    }
}
