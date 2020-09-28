package com.yufeng.hackerRank1;

public class Solution {

    static long getSequenceSum(int i, int j, int k) {
//        Long num1 = Long.valueOf(String.valueOf(i));
//        Long num2 = Long.valueOf(String.valueOf(j));
//        Long num3 = Long.valueOf(String.valueOf(k));
//
//
//
//
//        Long result = 0L;
//        for(Long p = num1; p <= num2; p++) {
//            result += p;
//        }
//
//        for(Long p = num2 - 1L; p >= num3; p--) {
//            result += p;
//        }

        Long num3 = Long.valueOf(String.valueOf(j));
        Long num2 = Math.max( Long.valueOf(String.valueOf(i)),  Long.valueOf(String.valueOf(k)));
        Long num1 = Math.min( Long.valueOf(String.valueOf(i)),  Long.valueOf(String.valueOf(k)));


       Long n1 = num3 - num1 + 1;
       Long sum1 = n1 * (num1 + num3) / 2;

       Long n2 = num3 - num2;
       Long sum2 = n2 * (num3 - 1 + num2) /2;


        return sum2 + sum1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(getSequenceSum(5, 9, 6));
    }
}
