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

