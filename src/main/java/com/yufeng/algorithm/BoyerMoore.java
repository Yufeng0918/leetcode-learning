package com.yufeng.algorithm;

public class BoyerMoore {

    public static void main(String[] args) {

        BoyerMoore boyerMoore = new BoyerMoore();
//        boyerMoore.generateGS("cabcab".toCharArray(), 6, new int[6], new boolean[6]);
        String main = "xxxxxxxxxxxxxxxxxxxxxxxxabcacabcbcba";
        String pattern = "cbbcabc";
        System.out.println(boyerMoore.bm(main.toCharArray(), main.length(), pattern.toCharArray(), pattern.length()));
//        abcacabcbcba
//         cbacabc
    }


    // 全局变量或成员变量
    private static final int SIZE = 256;

    // 构建坏字节
    private void generateBC(char[] pattern, int m, int[] badChars) {

        // badChars 大小是256，可以容纳所有的ascii码
        for (int i = 0; i < SIZE; ++i) {
            // 初始化bc
            badChars[i] = -1;
        }
        for (int i = 0; i < m; ++i) {
            // 计算b[i]的ASCII值
            int ascii = (int)pattern[i];
            badChars[ascii] = i;
        }
    }

    // 构建好字节后缀
    private void generateGS(char[] pattern, int m, int[] suffix, boolean[] prefix) {
        // 初始化
        for (int i = 0; i < m; ++i) {
            suffix[i] = -1;
            prefix[i] = false;
        }

        // b[0, i]
        // 从模式串头开始寻找，
        // j = 0, b[j]模式串中与b[m - 1]
        // j = 1, b[j]模式串中与b[m - 2]
        // 每次比较如果匹配，就说明从本字符起像左的字符也有可能匹配，所有继续比较
        for (int i = 0; i < m - 1; ++i) {
            int j = i;
            // 公共后缀子串长度
            int k = 0;
            // 与b[0, m-1]求公共后缀子串
            while (j >= 0 && pattern[j] == pattern[m-1-k]) {
                --j;
                ++k;
                //j+1表示公共后缀子串在b[0, i]中的起始下标
                suffix[k] = j+1;
            }
            //结束每轮suffix的寻找，如果找不到就是原值-1

            //如果前缀已经比较完了，而模式串也完了或者没用完，那么改前缀都是模式串的子传
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }

    // a,b表示主串和模式串；n，m表示主串和模式串的长度。
    public int bm(char[] main, int n, char[] pattern, int m) {
        // 记录模式串中每个字符最后出现的位置
        int[] badChars = new int[256];
        generateBC(pattern, m, badChars); // 构建坏字符哈希表
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(pattern, m, suffix, prefix);
        int i = 0; // j表示主串与模式串匹配的第一个字符
        while (i <= n - m) {
            int j;
            // 模式串从后往前匹配
            for (j = m - 1; j >= 0; --j) {
                // 坏字符对应模式串中的下标是j
                if (main[i+j] != pattern[j]) break;
            }
            if (j < 0) {
                // 匹配成功，返回主串与模式串第一个匹配的字符的位置
                return i;
            }

            //i + j 是主串中怀字节出现的对应位置
            //main[i + j] 主串中的怀字符
            //badChars[(int)main[i+j]] 坏字符在模式串的匹配的位置
            //x是根据坏字符计算出来的移动步数
            int x = j - badChars[(int)main[i+j]];
            int y = 0;

            // 如果有好后缀的话
            if (j < m-1) {
                y = moveByGS(j, m, suffix, prefix);
            }
            i = i + Math.max(x, y);
        }
        return -1;
    }

    // j表示坏字符对应的模式串中的字符下标;
    // m表示模式串长度
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        // 好后缀长度
        int k = m - 1 - j;
        if (suffix[k] != -1)
            return j - suffix[k] +1;

        // 没有和当初好后缀完全匹配的字符串，则继续寻找子串

        for (int r = j + 2 ; r <= m-1; ++r) {
            if (prefix[m-r] == true) {
                return r;
            }
        }
        return m;
    }
}
