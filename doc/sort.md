# 排序


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



## 线性排序

### 桶排序

桶排序，会用到“桶”，核心思想是将要排序的数据分到几个有序的桶里，每个桶里的数据再单独进行排序。如果要排序的数据有 n 个，我们把它们均匀地划分到 m 个桶内，每个桶里就有 k=n/m 个元素。**每个桶内部使用快速排序，时间复杂度为 O(k * logk)。m 个桶排序的时间复杂度就是 O(m * k * logk)，因为 k=n/m，所以整个桶排序的时间复杂度就是 O(n*log(n/m))**。当桶的个数 m 接近数据个数 n 时，log(n/m) 就是一个非常小的常量，这个时候桶排序的时间复杂度接近 O(n)。

![](../images/leetcode-73.jpg)

#### 桶限制条件

首先，要排序的数据需要很容易就能划分成 m 个桶，并且，**桶与桶之间有着天然的大小顺序。这样每个桶内的数据都排序完之后，桶与桶之间的数据不需要再进行排序。**

#### 适合外部排序

比如说我们有 10GB 的订单数据，我们希望按订单金额（假设金额都是正整数）进行排序，但是我们的内存有限，只有几百 MB，没办法一次性把 10GB 的数据都加载到内存中。我们可以先扫描一遍文件，看订单金额所处的数据范围。假设经过扫描之后我们得到，订单金额最小是 1 元，最大是 10 万元。我们将所有订单根据金额划分到 100 个桶里。



### 计数排序

计数排序其实是桶排序的一种特殊情况。当要排序的 n 个数据，所处的范围并不大的时候，比如最大值是 k，我们就可以把数据划分成 k 个桶。每个桶内的数据值都是相同的，省掉了桶内排序的时间。

![](../images/leetcode-74.jpg)

![](../images/leetcode-75.jpg)

当扫描到 3 时，我们可以从数组 C 中取出下标为 3 的值 7，也就是说，到目前为止，包括自己在内，分数小于等于 3 的考生有 7 个，也就是说 3 是数组 R 中的第 7 个元素（也就是数组 R 中下标为 6 的位置）。当 3 放入到数组 R 中后，小于等于 3 的元素就只剩下了 6 个了，所以相应的 C[3]要减 1，变成 6。

```JAVA

// 计数排序，a是数组，n是数组大小。假设数组中存储的都是非负整数。
public void countingSort(int[] a, int n) {
  if (n <= 1) return;

  // 查找数组中数据的范围
  int max = a[0];
  for (int i = 1; i < n; ++i) {
    if (max < a[i]) {
      max = a[i];
    }
  }

  int[] c = new int[max + 1]; // 申请一个计数数组c，下标大小[0,max]
  for (int i = 0; i <= max; ++i) {
    c[i] = 0;
  }

  // 计算每个元素的个数，放入c中
  for (int i = 0; i < n; ++i) {
    c[a[i]]++;
  }

  // 依次累加
  for (int i = 1; i <= max; ++i) {
    c[i] = c[i-1] + c[i];
  }

  // 临时数组r，存储排序之后的结果
  int[] r = new int[n];
  // 计算排序的关键步骤，有点难理解
  for (int i = n - 1; i >= 0; --i) {
    int index = c[a[i]]-1;
    r[index] = a[i];
    c[a[i]]--;
  }

  // 将结果拷贝给a数组
  for (int i = 0; i < n; ++i) {
    a[i] = r[i];
  }
}
```

**计数排序只能用在数据范围不大的场景中，如果数据范围 k 比要排序的数据 n 大很多，就不适合用计数排序了。**而且，**计数排序只能给非负整数排序，如果要排序的数据是其他类型的，要将其在不改变相对大小的情况下，转化为非负整数。**



### 基数排序

借助**稳定排序算法**，先按照最后一位来排序手机号码，然后，再按照倒数第二位重新排序，以此类推，最后按照第一位重新排序。经过 11 次排序之后，手机号码就都有序了。

![](../images/leetcode-76.jpg)

基数排序对要排序的数据是有要求的**，需要可以分割出独立的“位”来比较，而且位之间有递进的关系，如果 a 数据的高位比 b 数据大，那剩下的低位就不用比较了。除此之外，每一位的数据范围不能太大，要可以用线性排序算法来排序，否则，基数排序的时间复杂度就无法做到 O(n) 了。**



## 工业实现

![](../images/leetcode-77.jpg)

使用归并排序的情况其实并不多。**因为归并排序并不是原地排序算法，空间复杂度是 O(n)。**所以，如果要排序 100MB 的数据，除了数据本身占用的内存之外，排序算法还要额外再占用 100MB 的内存空间，空间耗费就翻倍了。**快速排序比较适合来实现排序函数。**

### 分区

**最理想的分区点是：被分区点分开的两个分区中，数据的数量差不多。**

+ 三数取中法
+ 随机法

### Glibc实现

+ qsort() 会优先使用归并排序来排序输入数据。选择分区点的方法就是“三数取中法”
+ 要排序的数据量比较大的时候，qsort() 会改为用快速排序算法来排序。
+ 元素的个数小于等于 4 时，qsort() 就退化为插入排序

O(n2) 时间复杂度的算法并不一定比 O(nlogn) 的算法执行时间长




## 习题

| 序号 | 题目次数                                                     | 次数 |
| ---- | ------------------------------------------------------------ | ---- |
| 1122 | [数组的相对排序](https://leetcode-cn.com/problems/relative-sort-array/) | 1    |
| 242  | [有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/) | 2    |
| 56   | [合并区间](https://leetcode-cn.com/problems/merge-intervals/) | 1    |
| 1244 | [力扣排行榜](https://leetcode-cn.com/problems/design-a-leaderboard/) | 1    |
| 493  | [ 翻转对](https://leetcode-cn.com/problems/reverse-pairs/)   | 1    |
