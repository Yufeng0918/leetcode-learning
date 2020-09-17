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

```JAVA

```

