# Recursion

| 序号 | 题目                           | 连接                                                         | 次数 |
| ---- | ------------------------------ | ------------------------------------------------------------ | ---- |
| 70   | 爬楼梯                         | https://leetcode-cn.com/problems/climbing-stairs/            | 2    |
| 22   | 括号生成                       | https://leetcode-cn.com/problems/generate-parentheses/       | 2    |
| 226  | 翻转二叉树                     | https://leetcode-cn.com/problems/invert-binary-tree/description/ | 2    |
| 96   | 验证二叉搜索树                 | https://leetcode-cn.com/problems/validate-binary-search-tree/ | 1    |
| 104  | 二叉树的最大深度               | https://leetcode-cn.com/problems/maximum-depth-of-binary-tree | 1    |
| 111  | 二叉树的最小深度               | https://leetcode-cn.com/problems/minimum-depth-of-binary-tree | 1    |
| 297  | 二叉树的序列化与反序列化       | https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/ | 1    |
| 236  | 二叉树的最近公共祖先           | https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/ | 1    |
| 105  | 从前序与中序遍历序列构造二叉树 | https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/ | 1    |
| 77   | 组合                           | https://leetcode-cn.com/problems/combinations/               | 1    |
| 46   | 全排列                         | https://leetcode-cn.com/problems/permutations/               | 1    |
| 47   | 全排列 II                      | https://leetcode-cn.com/problems/permutations-ii/            | 1    |
| 50   | Pow(x, n)                      | https://leetcode-cn.com/problems/powx-n/                     | 1    |
| 78   | 子集                           | https://leetcode-cn.com/problems/subsets/                    | 1    |
| 169  | 多数元素                       | https://leetcode-cn.com/problems/majority-element/description/ | 1    |
| 17   | 电话号码的字母组合             | https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/ | 1    |
| 51   | N皇后                          | https://leetcode-cn.com/problems/n-queens/                   | 1    |





## 代码模板

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



## 括号生产

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

