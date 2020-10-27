# 递归

## 概述

### 递归条件

+ 一个问题的解可以分解为几个子问题的解

+ 这个问题与分解之后的子问题，除了数据规模不同，求解思路完全一样
+ 存在递归终止条件



### 递归代码

#### 递归关键

写递归代码的关键

+ 找到如何将大问题分解为小问题的规律，并且基于此写出递推公式
+ 推敲终止条件，最后将递推公式和终止条件翻译成代码

**编写递归代码的关键是，只要遇到递归，我们就把它抽象成一个递推公式，不用想一层层的调用关系，不要试图用人脑去分解递归的每个步骤。**

#### 递归问题

##### 堆栈溢出

函数调用会使用栈来保存临时变量。每调用一个函数，都会将临时变量封装为栈帧压入内存栈，等函数执行完成返回时，才出栈。系统栈或者虚拟机栈空间一般都不大。如果递归求解的数据规模很大，调用层次很深，一直压入栈，就会有堆栈溢出的风险。

我们可以通过在代码中限制递归调用的最大深度的方式来解决这个问题。递归调用超过一定深度（比如 1000）之后，我们就不继续往下再递归了，直接返回报错。

```JAVA

// 全局变量，表示递归的深度。
int depth = 0;

int f(int n) {
  ++depth；
  if (depth > 1000) throw exception;
  
  if (n == 1) return 1;
  return f(n-1) + 1;
}
```

##### 重复计算

![](../images/leetcode-69.jpg)



### 分治算法

分治算法（divide and conquer）的核心思想其实就是四个字，分而治之 ，也就是将原问题划分成 n 个规模较小，并且结构与原问题相似的子问题，递归地解决这些子问题，然后再合并其结果，就得到原问题的解。

**分治算法是一种处理问题的思想，递归是一种编程技巧。**

+ 分解：将原问题分解成一系列子问题
+ 解决：递归地求解各个子问题，若子问题足够小，则直接求解
+ 合并：将子问题的结果合并成原问题。

#### 逆序对

我们用分治算法来试试。我们套用分治的思想来求数组 A 的逆序对个数。我们可以将数组分成前后两半 A1 和 A2，分别计算 A1 和 A2 的逆序对个数 K1 和 K2，**然后再计算 A1 与 A2 之间的逆序对个数 K3。那数组 A 的逆序对个数就等于 K1+K2+K3。**

![](../images/leetcode-70.jpg)

归并排序中有一个非常关键的操作，就是将两个有序的小数组，合并成一个有序的数组。实际上，**在这个合并的过程中，我们就可以计算这两个小数组的逆序对个数了。每次合并操作，我们都计算逆序对个数，把这些计算出来的逆序对个数求和，就是这个数组的逆序对个数了。**

![](../images/leetcode-71.jpg)

```JAVA

private int num = 0; // 全局变量或者成员变量

public int count(int[] a, int n) {
  num = 0;
  mergeSortCounting(a, 0, n-1);
  return num;
}

private void mergeSortCounting(int[] a, int p, int r) {
  if (p >= r) return;
  int q = (p+r)/2;
  mergeSortCounting(a, p, q);
  mergeSortCounting(a, q+1, r);
  merge(a, p, q, r);
}

private void merge(int[] a, int p, int q, int r) {
  int i = p, j = q+1, k = 0;
  int[] tmp = new int[r-p+1];
  while (i<=q && j<=r) {
    if (a[i] <= a[j]) {
      tmp[k++] = a[i++];
    } else {
      num += (q-i+1); // 统计p-q之间，比a[j]大的元素个数
      tmp[k++] = a[j++];
    }
  }
  while (i <= q) { // 处理剩下的
    tmp[k++] = a[i++];
  }
  while (j <= r) { // 处理剩下的
    tmp[k++] = a[j++];
  }
  for (i = 0; i <= r-p; ++i) { // 从tmp拷贝回a
    a[p+i] = tmp[i];
  }
}
```

#### 海量数据处理

比如，给 10GB 的订单文件按照金额排序这样一个需求，看似是一个简单的排序问题，但是因为数据量大，有 10GB，而我们的机器的内存可能只有 2、3GB 这样子，无法一次性加载到内存，也就无法通过单纯地使用快排、归并等基础算法来解决了。

要解决这种数据量大到内存装不下的问题，我们就可以利用分治的思想。我们可以将海量的数据集合根据某种方法，划分为几个小的数据集合，每个小的数据集合单独加载到内存来解决，然后再将小数据集合合并成大数据集合。



### 代码模板

最终答案在叶子节点，递归不需要回溯

最终答案在根节点，回溯解法

```JAVA
private static int divide_conquer(Problem problem, ) {
  
  // terminator
  if (problem == NULL) {
    int res = process_last_result();
    return res;     
  }
  
  //split to sub problem
  subProblems = split_problem(problem)
  
  // pass sub problem  
  res0 = divide_conquer(subProblems[0])
  res1 = divide_conquer(subProblems[1])
  
  // merge sub problem result or additional processing  
  result = process_result(res0, res1);
  return result;
}
```



## 习题

| 序号 | 题目                           | 连接                                                         | 次数 |
| ---- | ------------------------------ | ------------------------------------------------------------ | ---- |
| 70   | 爬楼梯                         | https://leetcode-cn.com/problems/climbing-stairs/            | 2    |
| 22   | 括号生成                       | https://leetcode-cn.com/problems/generate-parentheses/       | 2    |
| 77   | 组合                           | https://leetcode-cn.com/problems/combinations/               | 1    |
| 46   | 全排列                         | https://leetcode-cn.com/problems/permutations/               | 1    |
| 47   | 全排列 II                      | https://leetcode-cn.com/problems/permutations-ii/            | 1    |
| 50   | Pow(x, n)                      | https://leetcode-cn.com/problems/powx-n/                     | 1    |
| 78   | 子集                           | https://leetcode-cn.com/problems/subsets/                    | 1    |
| 169  | 多数元素                       | https://leetcode-cn.com/problems/majority-element/description/ | 1    |
| 17   | 电话号码的字母组合             | https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/ | 1    |
| 51   | N皇后                          | https://leetcode-cn.com/problems/n-queens/                   | 1    |




### 括号生产

树形分叉递归，**最终结果在树的叶子节点**

**终止条件**：当左括号和右括号都使用完

**分叉条件**

+ 左括号还有剩余

+ 右括号个数小于左括号

```JAVA
class Solution {
    public List<String> generateParenthesis(int n) {

        List<String> result = new LinkedList<>();
        if (n == 0) return result;

        generate(0, 0, n, "", result);
        return result;
    }

    public void generate(int left, int right, int limit, String curr, List<String> result) {
        // 终结条件
        if (left == limit && right == limit) result.add(curr);

        // 分支开叉向下
        if (left < limit) generate(left + 1, right, limit, curr + "(", result);
        if (right < left && right < limit) generate(left, right + 1, limit, curr + ")", result);
    }
}
```



### 验证二叉树

终止条件：当前节点已经是NULL，说明上一个节点要么是子节点，要么分支在另一侧，当前分支已经递归完毕

**当前判断**：当前节点必须小于上边界和大于下边界

**分叉条件**

+ 左子树的上边界是本节点
+ 右子数的下边界是本节点

**回溯**

+ 左子树是合法的二叉树
+ 右子树是合法的二叉树
+ 可以把非法条件都排除，那么余下的就是合法的二叉树及当前判断成立，左右子树是合法二叉树

```JAVA
class Solution {
    public boolean isValidBST(TreeNode root) {

        return validateNode(null, null, root);
    }

    public boolean validateNode(Integer upper, Integer lower, TreeNode node) {

        if (node == null) return true;

        if (upper != null && node.val >= upper) return false;
        if (lower != null && node.val <= lower) return false;

        if (!validateNode(node.val, lower, node.left)) return false;
        if (!validateNode(upper, node.val, node.right)) return false;

        return true;        
    }
}
```



### 前序序列化和反序列化

对于消费性的递归，比如前序序列化，每个节点创建以后需要消费当前字符串

+ 状态话的递归需要传递状态话的参数
+ String是muture，没有状态，**把String转为有状态的List**

```JAVA
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        if (root == null) return "n";

        return root.val + "," + serialize(root.left) + ","  + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
        if ("n".equals(data)) return null;

        String[] arr = data.split(",");
        List<String> list = new LinkedList<>();
        for(String item: arr) {
            list.add(item);
        }

        return  deserializeTreeNode(list);
    }


    public TreeNode deserializeTreeNode(List<String> list) {

        if (list.size() == 0) return null;

        String curr = list.get(0);
        list.remove(0);
        if ("n".equals(curr)) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.valueOf(curr));
        TreeNode left = deserializeTreeNode(list);
        TreeNode right = deserializeTreeNode(list);

        node.left = left;
        node.right = right;
        return node;
    }
}
```

