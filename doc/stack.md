# 栈

## 概述

**栈是一种“操作受限”的线性表**，只允许在一端插入和删除数据。**后进者先出，先进者后出**，这就是典型的“栈”结构。

实际上，栈既可以用数组来实现，也可以用链表来实现。

+ 用数组实现的栈，我们叫作**顺序栈**
+ 用链表实现的栈，我们叫作**链式栈**



## 应用

### 表达式求值

![](../images/leetcode-32.jpg)

编译器就是通过两个栈来实现的。其中一个**保存操作数的栈**，另一个是**保存运算符的栈**。我们从左向右遍历表达式，当遇到数字，我们就直接压入操作数栈；当遇到运算符，就与运算符栈的栈顶元素进行比较。

+ 如果比运算符栈顶元素的优先级高，就将当前运算符压入栈
+ 如果比运算符栈顶元素的优先级低或者相同，从运算符栈中取栈顶运算符，从操作数栈的栈顶取 2 个操作数，然后进行计算，再把计算完的结果压入操作数栈，继续比较。



### 括号匹配

我们用栈来保存未匹配的左括号，从左到右依次扫描字符串。当扫描到左括号时，则将其压入栈中；当扫描到右括号时，从栈顶取出一个左括号。如果能够匹配，比如“(”跟“)”匹配，“[”跟“]”匹配，“{”跟“}”匹配，则继续扫描剩下的字符串。如果扫描的过程中，遇到不能配对的右括号，或者栈中没有数据，则说明为非法格式。



### 浏览器

顺序查看了 a，b，c 三个页面，我们就依次把 a，b，c 压入栈，这个时候，两个栈的数据就是这个样子

![](../images/leetcode-33.jpg)

当你通过浏览器的后退按钮，从页面 c 后退到页面 a 之后，我们就依次把 c 和 b 从栈 X 中弹出，并且依次放入到栈 Y。这个时候，两个栈的数据就是这个样子：

![](../images/leetcode-34.jpg)

又点击前进按钮回到 b 页面，我们就把 b 再从栈 Y 中出栈，放入栈 X 中。此时两个栈的数据是这个样子

![](../images/leetcode-35.jpg)

通过页面 b 又跳转到新的页面 d 了，页面 c 就无法再通过前进、后退按钮重复查看了，所以需要清空栈 Y

![](../images/leetcode-35.jpg)



## 习题

| 序号 | 题目                                                         | 次数 |
| ---- | ------------------------------------------------------------ | ---- |
| 20   | [有效的括号](https://leetcode-cn.com/problems/valid-parentheses/) | 3    |
| 394  | [字符串解码](https://leetcode-cn.com/problems/decode-string/) | 2    |
| 155  | [最小栈](https://leetcode-cn.com/problems/min-stack/)        | 3    |
| 739  | [每日温度](https://leetcode-cn.com/problems/daily-temperatures/) | 4    |
| 84   | [柱状图中最大的矩形](https://leetcode-cn.com/problems/largest-rectangle-in-histogram/) | 3    |
| 42   | [接雨水](https://leetcode-cn.com/problems/trapping-rain-water/) | 3    |

### 常用技巧

+ 单调栈，递减单调还是递增单调
+ 确定左边界和右边界
+ 左边界：栈顶元素是左边界
+ 右边界：等待入栈当前元素，则是右边界
+ **所有元素入栈以后，栈中元素是否需要处理，如果需要处理是否可以用哨兵。**



### 每日温度

+ **栈是一个递减的单调栈**
+ 入栈：当前元素对应的温度小于栈顶元素
+ 出栈：当前元素对应的温度大于栈顶元素
+ 日期间隔：日期索引相减

```JAVA
class Solution {
    public int[] dailyTemperatures(int[] T) {

        if (T.length == 0) return new int[]{};

        LinkedList<Integer> stack = new LinkedList<>();
        int[] result = new int[T.length];
        stack.push(0);
        for(int i = 1; i < T.length; i++) {

            if (stack.size() == 0) {
                stack.addLast(i);
                continue;
            }

            while(stack.size() != 0 && T[i] > T[stack.getLast()]) {
                result[stack.getLast()] = i - stack.getLast();
                stack.removeLast();
            }
            stack.addLast(i);
        }
        return result;
    }
}
```



### 柱状图中最大的矩形

- **单调递增栈**
- **入栈**: 当前元素大于栈顶元素，**栈顶元素是当前元素的左边界**，可是当前元素还没有找到右边界，所以入栈
- **出栈**: 当前元素小于栈顶元素，**当前元素是栈顶元素的右边界 + 1**。此时有了左边界和右边界，应该出栈计算
- 宽计算方法
  - 栈不为空：右边界（不包括）- 左边界（包括）- 1
  - 栈为空：左边界是-1位置，右边界 + 1
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

利用前后哨兵简化代码，哨兵不会改变计算中的长和宽，所以对整体计算不会有影响

````java
class Solution {
    public int largestRectangleArea(int[] heights) {

        if (heights.length == 0) return 0;
        if (heights.length == 1) return heights[0];
        
        int[] temp = new int[heights.length + 2];
        for(int i = 0; i < heights.length; i++) {
            temp[i + 1] = heights[i];
        }
        heights = temp;
        Deque<Integer> s = new ArrayDeque<>();
        int area = 0, h, w;
        for(int i = 0; i < heights.length; i++) {
            
            while(s.size() != 0 && heights[i] < heights[s.peek()]) {
                h = heights[s.pop()];
                w = s.size() == 0 ? i : i - s.peek() - 1;
                area = Math.max(area, h * w);
            }
            s.push(i);
        }
        return area;
    }
}
````



### 接雨水

+ 单调递减栈
+ 左边界：当前pop以后，栈顶元素 + 1
+ 有边界：大于当前栈顶元素

```JAVA
class Solution {
    public int trap(int[] height) {

        if (height.length == 0 || height.length == 1) return 0;

        int area = 0, h, w;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < height.length; i++) {
            if (stack.size() == 0 || height[i] <= height[stack.peek()]) {
                stack.push(i);
                continue;
            }

            while(stack.size() != 0 && height[i] > height[stack.peek()]) {
                h = height[stack.pop()];
                if (stack.size() == 0) continue;
                h = Math.min(height[stack.peek()], height[i]) - h;
                w = i - stack.peek() - 1;
                area = area + h * w;
            }
            stack.push(i);
        }
        return area;
    }
}
```

