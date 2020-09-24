## 动态规划

| 序号 | 题目                                                         | 次数 |
| ---- | ------------------------------------------------------------ | ---- |
| 62   | [不同路径](https://leetcode-cn.com/problems/unique-paths/)   | 1    |
| 63   | [不同路径 II](https://leetcode-cn.com/problems/unique-paths-ii/) | 1    |
| 1143 | [最长公共子序列](https://leetcode-cn.com/problems/longest-common-subsequence/) | 1    |
| 70   | [爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)  | 1    |
| 120  | [三角形最小路径和](https://leetcode-cn.com/problems/triangle/) | 1    |
| 53   | [最大子序和](https://leetcode-cn.com/problems/maximum-subarray/) | 1    |
| 152  | [乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray/) | 1    |
| 198  | [打家劫舍](https://leetcode-cn.com/problems/house-robber/)   | 1    |
| 213  | [打家劫舍 II](https://leetcode-cn.com/problems/house-robber-ii/) | 1    |
| 121  | [买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/) | 3    |
| 122  | [买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/) | 2    |
| 309  | [最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/) | 1    |
| 123  | [买卖股票的最佳时机 III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/) | 1    |
| 188  | [买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/) | 1    |
| 300  | [最长上升子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/) | 1    |
| 32   | [最长有效括号](https://leetcode-cn.com/problems/longest-valid-parentheses/) | 1    |
|      |                                                              |      |
|      |                                                              |      |
|      |                                                              |      |



## 概述

### 动态规划vs递归分支

+ **共性**：找到重复的子问题
+ **差异**：最优子结构，中途可以淘汰次优解



### 关键

#### 状态定义/转移

+ 找到状态和选择
  + **状态**：有几种参数决定状态就是几维DP
  + **选择**：从上一个状态迁移到现在状态有几种选择，并从中择优
+ 明确 dp 数组/函数的定义 
+ 寻找状态之间的关系

#### 代码

+ 最优子结构 opt[n] = best_of(opt[n-1], opt[n -2], ...)
+ 存储中间状态 opt[i]
+ 递推公式

```python
for 状态1 in 状态1的所有取值：
    for 状态2 in 状态2的所有取值：
        for ...
            dp[状态1][状态2][...] = 择优(选择1，选择2...)
```