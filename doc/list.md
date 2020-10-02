# 链表

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

| 序号 | 题目                                                         | 连接                                                       | 次数 |
| ---- | ------------------------------------------------------------ | ---------------------------------------------------------- | ---- |
| 206  | 反转链表                                                     | https://leetcode-cn.com/problems/reverse-linked-list/      | 3    |
| 141  | 环形链表                                                     | https://leetcode-cn.com/problems/linked-list-cycle         | 2    |
| 24   | 两两交换链表中的节点                                         | https://leetcode-cn.com/problems/swap-nodes-in-pairs/      | 2    |
| 142  | 环形链表 II                                                  | https://leetcode-cn.com/problems/linked-list-cycle-ii/     | 2    |
| 25   | K 个一组翻转链表                                             | https://leetcode-cn.com/problems/reverse-nodes-in-k-group/ | 2    |



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