# 栈/队列



| 序号 | 题目               | 连接                                                         | 次数 |
| ---- | ------------------ | ------------------------------------------------------------ | ---- |
| 20   | 有效的括号         | https://leetcode-cn.com/problems/valid-parentheses/          | 2    |
| 155  | 最小栈             | https://leetcode-cn.com/problems/min-stack/                  | 2    |
| 84   | 柱状图中最大的矩形 | https://leetcode-cn.com/problems/largest-rectangle-in-histogram | 2    |
| 239  | 滑动窗口最大值     | https://leetcode-cn.com/problems/sliding-window-maximum      | 2    |
| 641  | 设计循环双端队列   | https://leetcode-cn.com/problems/design-circular-deque       | 2    |
| 42   | 接雨水             | https://leetcode-cn.com/problems/trapping-rain-water/        | 2    |



## 柱状图中最大的矩形

- 入栈: 当前元素如果大于上一个元素，说明上一个元素是当前元素的左边边界，可是当前元素还没有找到右边界，所以入栈
- 出栈: 当前元素小于上一个元素，说明当前元素是上一个元素的右边边界。此时有了左边界和右边界，应该进行计算
- 宽计算方法:  右边界（不包括）- 左边界（包括）- 1
- 还在栈中的数字一定是从小到大排列，因为比当前元素小的已经被弹出栈中

```java
class Solution {
    public int largestRectangleArea(int[] heights) {

        if (heights == null) return 0;
        if (heights.length == 1) return heights[0];

        Stack<Integer> stack = new Stack<>();

        int area = 0;
        for(int i = 0; i < heights.length; i++) {

            while(!stack.isEmpty() && heights[stack.peek()] > heights[i]) {

                int height = heights[stack.pop()];
                int width;
                if (!stack.isEmpty()) {
                    width = i - stack.peek() - 1;
                } else {
                    width = i;
                }
                area = Math.max(area, height * width);
            }
            stack.push(i);
        }

        
        int len = stack.pop() + 1;
        while (!stack.isEmpty()) {
            int height = heights[stack.pop()];
            int width;
            if (!stack.isEmpty()) {
                width = len -stack.peek() - 1;
            } else {
                width = len;
            }
            area = Math.max(area, width * height);
        }
        return area;
    }
}
```



## 双端队列在Array中设计

- Head 是头节点
- Tail 是尾节点的下一个节点
- 通过 Head == Tail 来判断当前链条是否是空，(Tail + 1) % Capactiy == Head 来判断是否链表已满
- 往前移动 (index + 1) % capactiy
- 往后移动 (index + 1 + capacity) % capacity

```java
**class MyCircularDeque {

    /** Initialize your data structure here. Set the size of the deque to be k. */
    int[] arr;
    int front;
    int rear;
    int capacity;
    public MyCircularDeque(int k) {
        capacity = k + 1;
        arr = new int[capacity];
        front = 0;
        rear = 0;
        
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) return false;
        
        front = (front - 1 + capacity) % capacity;
        arr[front] = value;
        return true; 
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) return false;

        arr[rear] = value;
        rear = (rear + 1) % capacity;
        return true;
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) return false;
            
        front = (front + 1) % capacity;
        return true;
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) return false;

        rear = (rear - 1 + capacity) % capacity;
        return true;
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty()) return -1;

        return arr[front];
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty()) return -1;

        return arr[(rear - 1 + capacity) % capacity];
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return front == rear;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
}**
```