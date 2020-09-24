package com.yufeng.leetcode085;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] matrix = {
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '0'}
//                {'1', '0'},
//                {'1', '0'}
                {'0','1','1','0','1'},
                {'1','1','0','1','0'},
                {'0','1','1','1','0'},
                {'1','1','1','1','0'},
                {'1','1','1','1','1'},
                {'0','0','0','0','0'}
        };

        System.out.println(solution.maximalRectangle(matrix));
    }
    
    public int maximalRectangle(char[][] matrix) {

        if (matrix.length == 0) return 0;
        if (matrix[0].length == 0) return 0;

        int r = matrix.length;
        int c = matrix[0].length;

        int[][] dpV = new int[r][c];
        int[][] dpH = new int[r][c];
        dpV[0][0] = matrix[0][0] - '0';
        dpH[0][0] = matrix[0][0] - '0';
        int max = matrix[0][0] - '0';

        for(int i = 1; i < c; i++) {
            if (matrix[0][i] == '0') {
                dpV[0][i] = 0;
                dpH[0][i] = 0;
            } else {
                dpH[0][i] = dpH[0][i - 1] + 1;
                dpV[0][i] = 1;
                max = Math.max(dpH[0][i], max);
            }
        }


        for(int i = 1; i < r; i++) {
            if (matrix[i][0] == '0') {
                dpV[i][0] = 0;
                dpH[i][0] = 0;
            } else {
                dpV[i][0] = dpV[i - 1][0] + 1;
                dpH[i][0] = 1;
                max = Math.max(dpV[i][0], max);
            }
        }

        for(int i = 1; i < r; i++) {
            for(int j = 1; j < c; j++) {

                int result = 0;
                if (matrix[i][j] == '0') {
                    dpV[i][j] = 0;
                    dpH[i][j] = 0;
                } else {
                    if (matrix[i - 1][j] != '0' && matrix[i][j - 1] == '0') {
                        dpV[i][j] = dpV[i - 1][j] + 1;
                        dpH[i][j] = 1;
                        result = dpV[i][j];
                    } else if (matrix[i - 1][j] == '0' && matrix[i][j - 1] != '0') {
                        dpH[i][j] = dpH[i][j - 1] + 1;
                        dpV[i][j] = 1;
                        result = dpH[i][j];
                    } else if (matrix[i - 1][j] == '0' && matrix[i][j - 1] == '0') {
                        dpV[i][j] = 1;
                        dpH[i][j] = 1;
                        result = 1;
                    } else {
                        dpV[i][j] = dpV[i - 1][j] + 1;
                        dpH[i][j] = dpH[i][j - 1] + 1;
//                        result = dpV[i][j] * dpH[i][j];

                        int v = dpV[i][j];
                        int idx = j -1;
                        result = v;
                        for(int k = 0; k < dpH[i][j] - 1; k++) {
                            v = Math.min(dpV[i][idx], v);
                            idx--;
                            result = Math.max(v * (k + 2), result);
                        }
//                        result = v * dpH[i][j];


                        int h = dpH[i][j];
                        idx = i - 1;
                        for(int k = 0; k < dpV[i][j] - 1; k++) {
                            h = Math.min(dpH[idx][j], h);
                            idx--;
                            result = Math.max(h * (k + 2), result);
                        }
//                        result = Math.max(h * dpV[i][j], result);
                    }
                }

                max = Math.max(max, result);
            }
        }

        return max;
    }
}
