# 链表

| 序号 | 题目                   | 连接                                                         | 次数 |
| ---- | ---------------------- | ------------------------------------------------------------ | ---- |
| 11   | 盛最多水的容器         | https://leetcode-cn.com/problems/container-with-most-water/  | 3    |
| 15   | 三数之和               | https://leetcode-cn.com/problems/3sum/                       | 3    |
| 283  | 移动零                 | https://leetcode-cn.com/problems/move-zeroes/                | 3    |
| 206  | 反转链表               | https://leetcode-cn.com/problems/reverse-linked-list/        | 2    |
| 141  | 环形链表               | https://leetcode-cn.com/problems/linked-list-cycle           | 2    |
| 24   | 两两交换链表中的节点   | https://leetcode-cn.com/problems/swap-nodes-in-pairs/        | 2    |
| 142  | 环形链表 II            | https://leetcode-cn.com/problems/linked-list-cycle-ii/       | 2    |
| 25   | K 个一组翻转链表       | https://leetcode-cn.com/problems/reverse-nodes-in-k-group/   | 2    |
| 189  | 旋转数组               | https://leetcode-cn.com/problems/rotate-array/               | 2    |
| 21   | 合并两个有序链表       | https://leetcode-cn.com/problems/merge-two-sorted-lists/     | 2    |
| 88   | 合并两个有序数组       | https://leetcode-cn.com/problems/merge-sorted-array/         | 2    |
| 1    | 两数之和               | https://leetcode-cn.com/problems/two-sum/                    | 2    |
| 66   | 加一                   | https://leetcode-cn.com/problems/plus-one/                   | 2    |
| 26   | 删除排序数组中的重复项 | https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/ | 2    |



## 左右指针移动

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
            max = Math.max(Math.min(height[i], height[j]) * (j - i), max);

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



## 已排序的左右指针

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



## 数组分区

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



## 链表翻转

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



## 两两交换链表中的节点

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



## 快慢指针

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