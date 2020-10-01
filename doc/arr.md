# 数组

## 概述

数组（Array）是一种线性表数据结构。它用一组连续的内存空间，来存储一组具有相同类型的数据。数组支持随机访问，根据**下标随机访问的时间复杂度为 O(1)**。

可以借助 CPU 的缓存机制，预读数组中的数据（预读一个page到内存中，所以并不仅仅读取一个内存地址），所以访问效率更高

数组容器（ArrayList）支持动态扩容，每次扩容1.5倍

![](../images/leetcode-07.jpg)





### 插入

如果在数组的末尾插入元素，不需要移动数据，时间复杂度为 O(1)。

如果在数组的开头插入元素，需要移动数据，最坏时间复杂度是 O(n)，平均时间复杂度为O(n)。

如果数组只是被当作一个存储数据的集合。如果要将某个数据插入到第 k 个位置，为了避免数据搬移，**直接将第 k 位的数据搬移到数组元素的最后，把新的元素直接放入第 k 个位置。**



### 删除

如果删除数组末尾的数据，则最好情况时间复杂度为 O(1)

如果删除开头的数据，则最坏情况时间复杂度为 O(n)

标记删除元素，在一个固定时间，把标记删除的元素清空。




## 习题

| 序号 | 题目                                                         | 提交 |
| ---- | ------------------------------------------------------------ | ---- |
| 11   | [盛最多水的容器](https://leetcode-cn.com/problems/container-with-most-water/) | 4    |
| 15   | [三数之和](https://leetcode-cn.com/problems/3sum/)           | 4    |
| 283  | [移动零](https://leetcode-cn.com/problems/move-zeroes/)      | 4    |
| 189  | [旋转数组](https://leetcode-cn.com/problems/rotate-array/)   | 3    |
| 88   | [合并两个有序数组](https://leetcode-cn.com/problems/merge-sorted-array/) | 4    |
| 1    | [两数之和](https://leetcode-cn.com/problems/two-sum/)        | 4    |
| 66   | [加一](https://leetcode-cn.com/problems/plus-one/)           | 3    |
| 26   | [删除排序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/) | 4    |
| 80   | [删除排序数组中的重复项 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/) | 3    |
| 27   | [移除元素](https://leetcode-cn.com/problems/remove-element/) | 3    |
| 495  | [提莫攻击](https://leetcode-cn.com/problems/teemo-attacking/)3 | 2    |
| 414  | [第三大的数](https://leetcode-cn.com/problems/third-maximum-number/) | 3    |



### 左右指针-无序

左右两个指针，**li < ho 作为外层循环和指针移动条件**

指针并不是每次移动一次，**指针循环移动到符合规定的目标位置(预先存储)**

```java
class Solution {
    public int maxArea(int[] height) {

        int max = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            int loMax = height[i];
            int hiMax = height[j];
            // max = Math.max(Math.min(height[i], height[j]) * (j - i), max);

            // without this, TLE will occur
            if (height[i] <= height[j]) {
                while (i < j && height[i] <= loMax) ++i;
            } else {
                while (i < j && height[j] <= hiMax) --j;
            }
        }
        return max;
    }
}
```



### 左右指针-有序

**比较上一次的位置来避免重复数字**， 在移动指针的时候，先移动过滤到重复的数字，再次则在移动

指针移动时必须保证每种情况都移动
- 不相等的情况是移动左或者右
- **相等的情况应该两边同时移动**

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> answers = new ArrayList<>();
        
        for (int i = 0; i < nums.length - 2; i++) {
            
            
            int c = nums[i];
            
            if (c > 0) {
                break;
            }

            if (i > 0 && c == nums[i - 1]) {
                continue;
            }
            
            int l = i + 1;
            int r = nums.length - 1;

            while(l < r) {
                
                int leftN = nums[l];
                int rightN = nums[r];
                int sum = c + leftN + rightN;

                if (sum == 0) {

                    List<Integer> ans = new ArrayList<>();
                    ans.add(c);
                    ans.add(leftN);
                    ans.add(rightN);
                    answers.add(ans);

                    while (l < r && nums[l] == leftN) l++;
                    while (l < r && nums[r] == rightN) r--; 
                } else if (sum < 0) {
                    
                    while (l < r && nums[l] == leftN) l++; 
                } else {
                     while (l < r && nums[r] == rightN) r--; 
                }
            }
        }
        return answers;
    }
}
```



### 快慢指针-分区

分为左右两区，**两个指针**，一个快指针循环数组，一个边界指针
- 循环指针：循环数组
- 边界指针
  - **保持左边下一次的位置**，符合条件进行交换，只有交换后才更改边界指针
  - **符合条件最后一个元素是 bound - 1**
- 边界指针和循环指针**初始位置必须一致**

循环数组，循环指针指向符合左边的元素进行交换，边界指针加一

```java
class Solution {
    public void moveZeroes(int[] nums) {

        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, j);
                j++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```



### 双数组双指针

两个有序数组合并

+ 两个数组各维护一个指针
+ 合并的数组单独一个指针
+ 在两个数组都为空的循环里面，**每次当个数组的循环必须遵循外圈循环的条件**, 每个循环必须检查**m >= 0 && n >= 0** 

```JAVA
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        if (nums1.length == 0 || nums2.length == 0) return;

        int i = nums1.length - 1;
        m--;
        n--;
        while(m >= 0 && n >= 0) {
            while(m >= 0 && n >= 0 && nums1[m] >= nums2[n]) nums1[i--] = nums1[m--];
            while(m >= 0 && n >= 0 && nums1[m] < nums2[n]) nums1[i--] = nums2[n--];
        }

        while(m >= 0) nums1[i--] = nums1[m--];
        while(n >= 0) nums1[i--] = nums2[n--];
    }
}
```



### 边界选择

当数值是整数时，Integer可能并不适合用于做边界初始值，可以用长整形Long来做边界初始值

```JAVA
class Solution {
    public int thirdMax(int[] nums) {
        
        Long num1 = Long.MIN_VALUE, num2 = Long.MIN_VALUE, num3 = Long.MIN_VALUE;
        
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] > num1) {
                num1 = Long.valueOf(nums[i]);
            }
        }

        for(int i = 0; i < nums.length; i++) {
            if (nums[i] > num2 && nums[i] != num1.intValue()) {
                num2 = Long.valueOf(nums[i]);
            }
        }
        if (num2 == Long.MIN_VALUE) return num1.intValue();
        

        for(int i = 0; i < nums.length; i++) {
            if (nums[i] > num3 && nums[i] != num1.intValue() && nums[i] != num2.intValue()) {
                num3 = Long.valueOf(nums[i]);
            }
        }

        return num3 == Long.MIN_VALUE ? num1.intValue() : num3.intValue();
    }
}
```

