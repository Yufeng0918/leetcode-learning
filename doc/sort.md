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

![](../images/leetcode-01.png)



### 内存消耗

原地排序算法，就是特指空间复杂度是 O(1) 的排序算法。

### 稳定性

这个概念是说，如果待排序的序列中**存在值相等的元素，经过排序之后，相等元素之间原有的先后顺序不变**。

比如说，我们现在要给电商交易系统中的“订单”排序。订单有两个属性，一个是下单时间，另一个是订单金额。我们希望按照金额从小到大对订单数据排序。对于金额相同的订单，我们希望按照下单时间从早到晚有序。



借助稳定排序算法，**我们先按照下单时间给订单排序，注意是按照下单时间**，不是金额。排序完成之后，**用稳定排序算法，按照订单金额重新排序**。两遍排序之后，我们得到的订单数据就是按照金额从小到大排序，金额相同的订单按照下单时间从早到晚排序的。**稳定排序算法可以保持金额相同的两个对象，在排序之后的前后顺序不变。**

![](../images/leetcode-14.jpg)



## 基础排序

冒泡排序不管怎么优化，元素交换的次数是一个固定值，是原始数据的逆序度。插入排序是同样的，不管怎么优化，元素移动的次数也等于原始数据的逆序度。

冒泡排序的数据交换要比插入排序的数据移动要复杂，**冒泡排序需要 3 个赋值操作，而插入排序只需要 1 个。**

![](../images/leetcode-16.jpg)



### 冒泡排序

冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素进行比较，看是否满足大小关系要求。如果不满足就让它俩互换

![](../images/leetcode-15.jpg)

```JAVA
    public static void bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length - i -1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
```





### 插入排序

将数组中的数据分为两个区间，**已排序区间和未排序区间**。插入算法的核心思想是取未排序区间中的元素，在已排序区间中找到合适的插入位置将其插入，并保证已排序区间数据一直有序。

![](../images/leetcode-17.jpg)

```JAVA
    public static void insertionSort(int[] arr) {

       for(int i = 1; i < arr.length; i++) {

           int temp = arr[i];
           int j = i - 1;
           while (j >= 0 && temp < arr[j] ) {
               arr[j + 1] = arr[j];
               j--;
           }
           arr[j + 1] = temp;
       }
    }
```





### 选择排序

分已排序区间和未排序区间。但是选择排序每次会从未排序区间中找到最小的元素，将其放到已排序区间的末尾

![](../images/leetcode-18.jpg)

```JAVA
    public static void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {

            int minIdx = i;
            for(int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }
```



## 高级排序

### 归并排序

先把数组从中间分成前后两部分，然后对前后两部分分别排序，再将排好序的两部分合并在一起。归并排序使用的就是**分治思想**。

非原地，稳定排序，时间复杂度是 O(nlogn)

![](../images/leetcode-19.jpg)

```Java
    private static void mergeSort(int[] arr, int l, int r) {

        if (l >= r) {
            return;
        }

        int mid = l + ((r - l) >> 1);
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);

        int i = l;
        int j = mid + 1;
        int k = 0;
        int[] temp = new int[r - l + 1];
        while (i <= mid && j <= r) {
            temp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];

        for(int q = 0, p = l; q < temp.length; q++, p++) {
            arr[p] = temp[q];
        }
    }
```



### 快速排序

排序数组中下标从 p 到 r 之间的一组数据，选择 p 到 r 之间的任意一个数据作为 pivot（分区点）。我们遍历 p 到 r 之间的数据，将小于 pivot 的放到左边，将大于 pivot 的放到右边，将 pivot 放到中间

快排是一种原地、不稳定的排序算法。

![](../images/leetcode-20.jpg)

```Java
    private static  void quickSort(int[] arr, int l, int r) {

        if (l >= r)
            return;

        int pivot = arr[r];
        int idx = l;
        int temp;
        for(int i = l; i <= r ; i++) {
            if (arr[i] < pivot) {
                temp = arr[idx];
                arr[idx++] = arr[i];
                arr[i] = temp;
            }
        }
      
        temp = arr[idx];
        arr[idx] = arr[r];
        arr[r] = temp;

        quickSort(arr, l, idx - 1);
        quickSort(arr, idx + 1, r);
    }
```



## 其他排序

### 计数排序

找出待排序的数组中最大和最小的元素；

统计数组中每个值为i的元素出现的次数，存入数组C的第i项， 对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；

反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。