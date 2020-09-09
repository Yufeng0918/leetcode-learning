# 二分查找



| 序号 | 题目                       | 链接                                                         | 次数 |
| ---- | -------------------------- | ------------------------------------------------------------ | ---- |
| 69   | x 的平方根                 | https://leetcode-cn.com/problems/sqrtx/                      | 1    |
| 367  | 有效的完全平方数           | https://leetcode-cn.com/problems/valid-perfect-square/       | 1    |
| 33   | 搜索旋转排序数组           | https://leetcode-cn.com/problems/search-in-rotated-sorted-array/ | 1    |
| 74   | 搜索二维矩阵               | https://leetcode-cn.com/problems/search-a-2d-matrix/         | 1    |
| 153  | 寻找旋转排序数组中的最小值 | https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/ | 1    |
|      |                            |                                                              |      |
|      |                            |                                                              |      |
|      |                            |                                                              |      |
|      |                            |                                                              |      |
|      |                            |                                                              |      |
|      |                            |                                                              |      |



## 二分查找模板

```JAVA
public int binarySearch(int[] array, int target) {
    int left = 0, right = array.length - 1, mid;
    while (left <= right) {
        mid = (right - left) / 2 + left;

        if (array[mid] == target) {
            return mid;
        } else if (array[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    return -1;
}
```

