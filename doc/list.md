# 数组/链表


## 数组

数组（Array）是一种线性表数据结构。它用一组连续的内存空间，来存储一组具有相同类型的数据。数组支持随机访问，根据**下标随机访问的时间复杂度为 O(1)**。

可以借助 CPU 的缓存机制，预读数组中的数据（预读一个page到内存中，所以并不仅仅读取一个内存地址），所以访问效率更高

数组容器（ArrayList）支持动态扩容，每次扩容1.5倍

![](../images/leetcode-07.jpg)





**插入**

如果在数组的末尾插入元素，不需要移动数据，时间复杂度为 O(1)。

如果在数组的开头插入元素，需要移动数据，最坏时间复杂度是 O(n)，平均时间复杂度为O(n)。

如果数组只是被当作一个存储数据的集合。如果要将某个数据插入到第 k 个位置，为了避免数据搬移，**直接将第 k 位的数据搬移到数组元素的最后，把新的元素直接放入第 k 个位置。**

**删除**

如果删除数组末尾的数据，则最好情况时间复杂度为 O(1)

如果删除开头的数据，则最坏情况时间复杂度为 O(n)

标记删除元素，在一个固定时间，把标记删除的元素清空。



## 链表

链表不需要一块连续的内存空间，它通过“指针”将一组零散的内存块串联起来使用

![](../images/leetcode-11.jpg)



## 应用

LRU Least Recent Used, 最近最少使用

+ 如果此数据之前已经被缓存在链表中了，我们遍历得到这个数据对应的结点，并将其从原来的位置删除，然后再插入到链表的头部。
+ 如果此数据没有在缓存链表中，又可以分为两种情况
  + 如果此时缓存未满，则将此结点直接插入到链表的头部
  + 如果此时缓存已满，则链表尾结点删除，将新的数据结点插入链表的头部

```JAVA
class LRUCache {

    LinkedList<Integer> list;
    Map<Integer, Integer> map;
    int capacity;

    public LRUCache(int capacity) {
        this.list = new LinkedList<>();
        this.map = new HashMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        list.remove((Object)key);
        list.addFirst(key);
        return map.get(key);
    }
    
    public void put(int key, int value) {

        map.put(key, value);
        list.remove((Object)key);

        list.addFirst(key);
        if (list.size() > capacity) {
            key = list.get(capacity);
            list.removeLast();
            map.remove(key);
        }
    }
}
```



## 常用技巧

### 初始条件

可正可负， 是否处理0， 链表和数组是否有已知长度



### 哨兵

引入哨兵结点，在任何时候，不管链表是不是空，head 指针都会一直指向这个哨兵结点。我们也把这种有哨兵结点的链表叫带头链表

![](../images/leetcode-12.jpg)



### 边界

+ 如果链表为空时
+ 如果链表只包含一个结点时
+ 如果链表只包含两个结点时
+ 代码逻辑在处理头结点和尾结点的时候



### 画图

![](../images/leetcode-13.jpg)



## 习题

| 序号 | 题目                                                         | 连接                                                         | 次数 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- |
| 11   | [盛最多水的容器](https://leetcode-cn.com/problems/container-with-most-water/) |                                                              | 4    |
| 15   | 三数之和                                                     | https://leetcode-cn.com/problems/3sum/                       | 3    |
| 283  | 移动零                                                       | https://leetcode-cn.com/problems/move-zeroes/                | 3    |
| 189  | 旋转数组                                                     | https://leetcode-cn.com/problems/rotate-array/               | 2    |
| 21   | 合并两个有序链表                                             | https://leetcode-cn.com/problems/merge-two-sorted-lists/     | 2    |
| 88   | 合并两个有序数组                                             | https://leetcode-cn.com/problems/merge-sorted-array/         | 2    |
| 1    | 两数之和                                                     | https://leetcode-cn.com/problems/two-sum/                    | 2    |
| 66   | 加一                                                         | https://leetcode-cn.com/problems/plus-one/                   | 2    |
| 26   | 删除排序数组中的重复项                                       | https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/ | 2    |
| 495  | [提莫攻击](https://leetcode-cn.com/problems/teemo-attacking/) |                                                              | 1    |
| 414  | [第三大的数](https://leetcode-cn.com/problems/third-maximum-number/) |                                                              | 1    |
| 206  | 反转链表                                                     | https://leetcode-cn.com/problems/reverse-linked-list/        | 3    |
| 141  | 环形链表                                                     | https://leetcode-cn.com/problems/linked-list-cycle           | 2    |
| 24   | 两两交换链表中的节点                                         | https://leetcode-cn.com/problems/swap-nodes-in-pairs/        | 2    |
| 142  | 环形链表 II                                                  | https://leetcode-cn.com/problems/linked-list-cycle-ii/       | 2    |
| 25   | K 个一组翻转链表                                             | https://leetcode-cn.com/problems/reverse-nodes-in-k-group/   | 2    |


### 左右指针移动

- 左右两个指针，**li < ho 作为外层循环和指针移动条件**
- 指针并不是每次移动一次，**指针循环移动到符合规定的目标位置(预先存储)**

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



### 已排序的左右指针

- 比较上一次的位置来避免重复数字
- 在移动指针的时候，先移动过滤到重复的数字，再次则在移动
- 指针移动时必须保证每种情况都移动
  - 不相等的情况是移动左或者右
  - 相等的情况应该两边同时移动

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



### 数组分区

- 分为左右两区，一个两个指针
  - 循环指针：循环数组
  - 边界指针：保持左边下一次的位置，符合条件进行交换，只有交换后才更改边界指针
  - 边界指针和循环指针**初始位置必须一致**
- 循环数组，循环指针指向符合左边的元素进行交换，边界指针加一

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



### 链表翻转

- 找出最简单的case
- 假设上一步已经完成
- 递归反转
  - 最小任务是当前节点没有下一个节点
  - curr.next 必须设置为NULL，不然头节点会形成环链表
- 循环法
  - 循环到当前节点为NULL位置，NULL之前的节点都已经完成翻转
  - 如果只有一个节点，那么头节点还是执行一次，因为prev设置成NULL
- **head == null || head.next == null 为最基础单元**
- prev一定比curr先移动

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }
        return prev;
    }
}
class Solution {
    
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = head.next;
        ListNode newHead = reverseList(next);
        next.next = head;
        head.next = null;

        return newHead;
    }
}
```



### 两两交换链表中的节点

- 无论当前子链表是否翻转，都需要把主链连接到子链边

```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;
        ListNode next;
        
        while(curr != null) {
            
            // data initilization
            ListNode first = curr;
            ListNode second = curr.next;

            if (second == null) {
                // link if sublist no change
                if (prev == null) {
                    head = first;
                } else {
                    prev.next = first;
                }
                break;
            }
            next = second.next;

            // swap
            // link to second
            if (prev == null) {
                head = second;
            } else {
                prev.next = second;
            }
            // link to first
            second.next = first;
            first.next = null;
            // System.out.println("head: " + head);
            // System.out.println("second: " + second);

            //rotate to next
            prev = first;
            curr = next;
        }
        return head;
    }
}
```



### 快慢指针

- 快慢指针起始位置可以不同也可以相同
- 只需要比较快慢指针的整数倍即可，**比如起始位置相同，不能比较中间步数，第一次会被误杀**

```java
ListNode slow = head;
ListNode fast = head;
while (fast != null && slow!= null) {
    //slow move
    if (slow == null || slow.next == null) {
        return false;
    }
    slow = slow.next;
    
    //fast move two step
    if (fast.next == null || fast.next.next == null) {
        return false;
    }
    fast = fast.next.next;

    if (fast == slow) {
        return true;
    }
}
return false;
```