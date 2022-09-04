package com.yufeng.algorithm;

/**
 * reference:
 * https://mp.weixin.qq.com/s/MoRBHbS4hQXn7LcPdmHmIg
 * https://www.bilibili.com/video/BV1PD4y1o7nd?spm_id_from=333.337.search-card.all.click&vd_source=0a8918ca96089ed3924a1be1a2ae7112
 * https://www.bilibili.com/video/BV1M5411j7Xx/?spm_id_from=333.788&vd_source=0a8918ca96089ed3924a1be1a2ae7112
 */
public class Kmp {

    public static void main(String[] args) {
        System.out.print(kmp("aabaabaafa".toCharArray(), "aabaaf".toCharArray()));
    }


    // a, b分别是主串和模式串；n, m分别是主串和模式串的长度。
    public static int kmp(char[] str, char[] pattern) {

        int m = pattern.length;
        int[] next = getNexts(pattern);

        int j = 0;
        for (int i = 0; i < str.length; ++i) {

            // 当模式串前缀和主串匹配，但是当前字符不匹配
            // next[j - 1]是下一个可以匹配当前后缀的前缀
            // j = next[j - 1] + 1 把模式串的可匹配前缀移动到末尾，再进行比较
            while (j > 0 && str[i] != pattern[j]) {
                j = next[j - 1] ;
            }
            if (str[i] == pattern[j]) {
                j++;
            }
            // 找到匹配模式串的了
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

//    aabaaf
//    [0, 1, 0, 1, 2, 0]
    private static int[] getNexts(char[] pattern) {
        int[] next = new int[pattern.length];

        // j is prefix
        int j = 0;

        // i is suffix
        for (int i = 1; i < pattern.length; ++i) {

            //当已经有前缀匹配，但是下一个下标不匹配的时候
            while (j > 0 && pattern[j] != pattern[i]) {
                j = next[j - 1];
            }

            if (pattern[j] == pattern[i]) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
