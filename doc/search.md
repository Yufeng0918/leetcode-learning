# 二分查找

## 概述

二分查找是一种非常高效的查找算法，我们假设数据大小是 n，每次查找后数据都会缩小为原来的一半，也就是会除以 2。最坏情况下，直到查找区间被缩小为空，才停止。

![](../images/leetcode-78.jpg)

这是一个等比数列。其中 n/2k=1 时，k 的值就是总共缩小的次数。所以，经过了 k 次区间缩小操作，时间复杂度就是 O(k)。通过 n/2k=1，可以求得 k=log2n，**所以时间复杂度就是 O(logn)**。



### 局限性

**二分查找依赖的是顺序表结构，就是数组**。要原因是二分查找算法需要按照下标随机访问元素

**二分查找针对的是有序数据。**数据必须是有序的。如果数据没有序，我们需要先排序。

**数据量太小不适合二分查找**

**数据量太大也不适合二分查找**，底层需要依赖数组这种数据结构，而数组为了支持随机访问的特性，要求内存空间连续。比如，我们有 1GB 大小的数据，如果希望用数组来存储，那就需要 1GB 的连续内存空间。



### 变体

#### 查找第一个值等于给定值的元素

当 a[mid]等于要查找的值时，a[mid]就是我们要找的元素。如果我们求解的是第一个值等于给定值的元素，当 a[mid]等于要查找的值时，我们就需要确认一下这个 a[mid]是不是第一个值等于给定值的元素。

+ 如果 mid 等于 0，那这个元素已经是数组的第一个元素，那它肯定是我们要找的
+ 如果 mid 不等于 0，a[mid]的前一个元素 a[mid-1]不等于 value，那也说明 a[mid]就是我们要找的第一个值等于给定值的元素。

```JAVA
public int bsearch(int[] a, int n, int value) {
  int low = 0;
  int high = n - 1;
  while (low <= high) {
    int mid =  low + ((high - low) >> 1);
    if (a[mid] > value) {
      high = mid - 1;
    } else if (a[mid] < value) {
      low = mid + 1;
    } else {
      if ((mid == 0) || (a[mid - 1] != value)) return mid;
      else high = mid - 1;
    }
  }
  return -1;
}
```

#### 查找最后一个值等于给定值的元素

```JAVA
public int bsearch(int[] a, int n, int value) {
  int low = 0;
  int high = n - 1;
  while (low <= high) {
    int mid =  low + ((high - low) >> 1);
    if (a[mid] > value) {
      high = mid - 1;
    } else if (a[mid] < value) {
      low = mid + 1;
    } else {
      if ((mid == n - 1) || (a[mid + 1] != value)) return mid;
      else low = mid + 1;
    }
  }
  return -1;
}
```

#### 查找第一个大于等于给定值的元素

```JAVA
public int bsearch(int[] a, int n, int value) {
  int low = 0;
  int high = n - 1;
  while (low <= high) {
    int mid =  low + ((high - low) >> 1);
    if (a[mid] >= value) {
      if ((mid == 0) || (a[mid - 1] < value)) return mid;
      else high = mid - 1;
    } else {
      low = mid + 1;
    }
  }
  return -1;
}
```

#### 查找最后一个小于等于给定值的元素

```JAVA
public int bsearch7(int[] a, int n, int value) {
  int low = 0;
  int high = n - 1;
  while (low <= high) {
    int mid =  low + ((high - low) >> 1);
    if (a[mid] > value) {
      high = mid - 1;
    } else {
      if ((mid == n - 1) || (a[mid + 1] > value)) return mid;
      else low = mid + 1;
    }
  }
  return -1;
}
```



## 模板

+ **循环条件**， 注意是 low<=high，而不是 low<high
+ **mid 的取值**， 实际上，mid=(low+high)/2 这种写法是有问题的。因为如果 low 和 high 比较大的话，两者之和就有可能会溢出。
+ **low 和 high 的更新**， low=mid+1，high=mid-1。注意这里的 +1 和 -1

```JAVA
public int bsearch(int[] a, int n, int value) {
  int low = 0;
  int high = n - 1;

  while (low <= high) {
    int mid = low+((high-low)>>1);
    if (a[mid] == value) {
      return mid;
    } else if (a[mid] < value) {
      low = mid + 1;
    } else {
      high = mid - 1;
    }
  }
  return -1;
}
```



## 习题

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

