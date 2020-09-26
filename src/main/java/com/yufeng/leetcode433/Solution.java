package com.yufeng.leetcode433;

public class Solution {

    public static void main(String[] args) {
        System.out.println(~0);
        //00 -> 11
        System.out.println(~0 << 2);
        //1100
        System.out.println(~3);
        //0011 -> 1100
        System.out.println(9 & (~0 << 3));

        System.out.println((9 >> 3) & 1);

        System.out.println(1);
        System.out.println(1 << 2);
        System.out.println(9 & (1 << 2));

        int a = 0b10101;
        System.out.println((a >> 0) &1);

        System.out.println(8 | (1 << 2));
        System.out.println(12 & (~(1 << 2)));
//        System.out.println((8 >> 3));
//        System.out.println((1 << (4 - 1)));
//        System.out.println(8 & (1 << (4 - 1)));

        System.out.println(16 & (15));
        System.out.println(12 & (-12));
        //01100
        //10011 -> 10100
        //00100

        System.out.println((1 | 0));

        System.out.println(Integer.numberOfLeadingZeros(5));
        System.out.println((1 << 2) -1 ) ;


        int b1 = 3;
        int b2 = 6;
        b1 = b1 ^ b2;
        b2 = b1 ^ b2;
        b1 = b1 ^ b2;
        System.out.println(b1 + " " + b2);
    }
}
