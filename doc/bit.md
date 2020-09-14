# 位运算

| 序号 | 题目                                                         | 次数 |
| ---- | ------------------------------------------------------------ | ---- |
| 191  | [位1的个数](https://leetcode-cn.com/problems/number-of-1-bits/) | 1    |
| 231  | [2的幂](https://leetcode-cn.com/problems/power-of-two/)      | 1    |
| 190  | [颠倒二进制位](https://leetcode-cn.com/problems/reverse-bits/) | 1    |
| 338  | [比特位计数](https://leetcode-cn.com/problems/counting-bits/) | 1    |
| 51   | [N 皇后](https://leetcode-cn.com/problems/n-queens/)         |      |
| 52   | [N皇后 II](https://leetcode-cn.com/problems/n-queens-ii/)    |      |



## 技巧总结

第n位是从0开始计算

| 法则                      | 算式                      |
| ------------------------- | ------------------------- |
| 将最右边n位清零           | x & (~0 << n)             |
| 获取第n位的值（0 或者 1） | (x >> n) & 1              |
| 获取第n位的幂值           | x & (1 << n)              |
| 将第n位设置为1            | x \| (1 << n)             |
| 将第n位设置为0            | x & (~(1<<n))             |
| 判断奇数偶数              | x & 1 == 1     x & 0 == 0 |
| 清空最低位的1             | x & (x - 1)               |
| 获取最低位的1             | x & -x                    |

