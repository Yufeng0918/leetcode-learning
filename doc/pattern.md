# 排序

| 序号 | 题目次数                                                     | 次数 |
| ---- | ------------------------------------------------------------ | ---- |
| 1122 | [数组的相对排序](https://leetcode-cn.com/problems/relative-sort-array/) | 1    |
| 242  | [有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/) | 2    |
| 56   | [合并区间](https://leetcode-cn.com/problems/merge-intervals/) | 1    |
| 1244 | [力扣排行榜](https://leetcode-cn.com/problems/design-a-leaderboard/) | 1    |
| 493  | [ 翻转对](https://leetcode-cn.com/problems/reverse-pairs/)   |      |
|      |                                                              |      |
|      |                                                              |      |



## 概述

### 暴力算法

在主串中，检查起始位置分别是 0、1、2…n-m 且长度为 m 的 n-m+1 个子串，看有没有跟模式串匹配的

![](../images/leetcode-21.jpg)



### Rabin-Karp 算法

通过哈希算法对主串中的 n-m+1 个子串分别求哈希值，然后逐个与模式串的哈希值比较大小。

+ 如果哈希值不相等，就说明肯定不匹配
+ 如果某个子串的哈希值与模式串相等，那就说明对应的子串和模式串匹配了

![](../images/leetcode-22.jpg)

```Java
public static int rabinKarpSerach(String txt, String pat) {
  int M = pat.length();
  int N = txt.length();
  int i, j;
  int patHash = 0, txtHash = 0;

  for (i = 0; i < M; i++) {
    patHash = (D * patHash + pat.charAt(i)) % Q;
    txtHash = (D * txtHash + txt.charAt(i)) % Q;
  }
  
  int highestPow = 1;  // pow(256, M-1)
  for (i = 0; i < M - 1; i++)
    highestPow = (highestPow * D) % Q;

  for (i = 0; i <= N - M; i++) { // 枚举起点
    if (patHash == txtHash) {
      for (j = 0; j < M; j++) {
        if (txt.charAt(i + j) != pat.charAt(j))
          break;
      }
      if (j == M)
        return i;
    }
    if (i < N - M) {
      txtHash = (D * (txtHash - txt.charAt(i) * highestPow) + txt.charAt(i + M)) % Q;
      if (txtHash < 0)
        txtHash += Q;
    }
  }
  return -1;
}
```



### Boyer-Moore 算法

BM 算法包含两部分，分别是坏字符规则（bad character rule）和好后缀规则（good suffix shift）

#### 坏字符规则

按照模式串下标从大到小的顺序，倒着匹配的。发现某个字符没法匹配的时候。把这个没有匹配的字符叫作坏字符（主串中的字符）

![](../images/leetcode-23.jpg)



当发生不匹配的时候，我们把坏字符对应的模式串中的字符下标记作 si。

+ 如果坏字符在模式串中存在，我们把这个坏字符在模式串中的下标记作 xi。
+ 如果不存在，我们把 xi 记作 -1。

**那模式串往后移动的位数就等于 si-xi。**

![](../images/leetcode-24.jpg)



#### 好后缀规则

把已经匹配的 bc 叫作好后缀，记作{u}。拿它在模式串中查找，如果找到了另一个跟{u}相匹配的子串{u'}，那我们就将模式串滑动到子串{u'}与主串中{u}对齐的位置。

![](../images/leetcode-25.jpg)

当模式串滑动到前缀与主串中{u}的后缀有部分重合的时候，并且重合的部分相等的时候，就有可能会存在完全匹配的情况。

![](../images/leetcode-26.jpg)

+ 好后缀在模式串中，是否有另一个匹配的子串，
+ 好后缀的后缀子串，是否存在跟模式串的前缀子串匹配的

```JAVA
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
```



### KMP算法

KMP 算法的核心思想，我们假设主串是 a，模式串是 b。在模式串与主串匹配的过程中，当遇到不可匹配的字符的时候，我们希望找到一些规律，可以将模式串往后多滑动几位，跳过那些肯定不会匹配的情况。

把好前缀的所有后缀子串中，最长的可匹配前缀子串的那个后缀子串，叫作**最长可匹配后缀子串**；对应的前缀子串，叫作最**长可匹配前缀子串。**

![](../images/leetcode-27.jpg)



提前构建一个数组，用来存储模式串中每个前缀（这些前缀都有可能是好前缀）的最长可匹配前缀子串的结尾字符下标。我们把这个**数组定义为 next 数组，很多书中还给这个数组起了一个名字，叫失效函数**

![](../images/leetcode-28.jpg)



如果 next[i-1]=k-1，也就是说，子串 b[0, k-1]是 b[0, i-1]的最长可匹配前缀子串。如果子串 b[0, k-1]的下一个字符 b[k]，与 b[0, i-1]的下一个字符 b[i]匹配，那子串 b[0, k]就是 b[0, i]的最长可匹配前缀子串

![](../images/leetcode-29.jpg)



假设 b[0, i]的最长可匹配后缀子串是 b[r, i]。如果我们把最后一个字符去掉，那 b[r, i-1]肯定是 b[0, i-1]的可匹配后缀子串，但不一定是最长可匹配后缀子串。那么就可以考察 **b[0, i-1]的次长可匹配后缀子串 b[x, i-1]对应的可匹配前缀子串 b[0, i-1-x]的下一个字符 b[i-x]是否等于 b[i]**。如果等于，那 b[x, i]就是 b[0, i]的最长可匹配后缀子串。

![](../images/leetcode-30.jpg)

![](../images/leetcode-31.jpg)

```java
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
```

