package com.yufeng.algorithm;

public class Kmp {

    public static void main(String[] args) {
//        getNexts("ababacd".toCharArray());
        System.out.println(kmp("ababaeabac".toCharArray(), "ababacd".toCharArray()));
    }


    // a, b分别是主串和模式串；n, m分别是主串和模式串的长度。
    public static int kmp(char[] a, char[] b) {
        int n = a.length;
        int m = b.length;
        int[] next = getNexts(b);

        //j是模式串下标
        int j = 0;

        //i是主串下标
        for (int i = 0; i < n; ++i) {

            // 当模式串前缀和主串匹配，但是当前字符不匹配
            // next[j - 1]是下一个可以匹配当前后缀的前缀
            // j = next[j - 1] + 1 把模式串的可匹配前缀移动到末尾，再进行比较
            while (j > 0 && a[i] != b[j]) {
                j = next[j - 1] + 1;
            }
            if (a[i] == b[j]) {
                ++j;
            }
            // 找到匹配模式串的了
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    // b表示模式串，m表示模式串的长度
    // [-1, -1, 0, 1, 2, -1, -1]
    private static int[] getNexts(char[] b) {
        int m = b.length;
        int[] next = new int[m];
        next[0] = -1;

        // 在长度从[0-1]之前，最长匹配前缀的下标
        int k = -1;

        //寻找每个子串的最长匹配前缀
        for (int i = 1; i < m; ++i) {

            //当已经有前缀匹配，但是下一个下标不匹配的时候
            while (k != -1 && b[k + 1] != b[i]) {
                //把下标往左移，因为已经[0, k]时，最长匹配子串的下标
                //直接移到最长匹配的下标
                k = next[k];
            }

            //当有字符和b[0]匹配时，那么匹配下标自增
            //如果下标持续匹配， b[0] == b[i], b[1] == b[i + 1], b[2] == b[i + 2]
            // k 和 i 同时递增
            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }
}
