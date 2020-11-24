package com.yufeng.leetcode542;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    int[][] m = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] ans = solution.updateMatrix(matrix);
        for(int i = 0; i < ans.length; i++) {
            for(int j = 0; j < ans[i].length; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }


    public int[][] updateMatrix(int[][] matrix) {

        if (matrix.length == 0 || matrix[0].length == 0) return new int[0][0];

        int[][] ans = new int[matrix.length][matrix[0].length];
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    bfs(matrix, ans, visited, i, j);
                }
            }
        }
        return ans;
    }

    public void bfs(int[][] matrix, int[][] ans, boolean[][] visited, int i, int j) {

//        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i, j});
        visited[i][j] = true;

        int step = 1, r, c, size;
        int[] curr;
        while(q.size() != 0) {
            size = q.size();
            for(int k = 0; k < size; k++) {
                curr = q.poll();

                for(int g = 0; g < m.length; g++) {
                    r = curr[0] + m[g][0];
                    if (r < 0 || r >= matrix.length) continue;
                    c = curr[1] + m[g][1];
                    if (c < 0 || c >= matrix[r].length) continue;
                    if (matrix[r][c] == 0) continue;

                    if (visited[r][c] == false || (visited[r][c] == true && ans[r][c] > step && ans[r][c] != 0)) {
                        visited[r][c] = true;
                        q.offer(new int[]{r, c});
                        ans[r][c] = step;
                    }
                }
            }
            step++;
        }
        // return step;
    }
}