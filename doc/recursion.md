# Recursion

| 序号 | 题目                           | 连接                                                         | 次数 |
| ---- | ------------------------------ | ------------------------------------------------------------ | ---- |
| 70   | 爬楼梯                         | https://leetcode-cn.com/problems/climbing-stairs/            | 2    |
| 22   | 括号生成                       | https://leetcode-cn.com/problems/generate-parentheses/       | 2    |
| 226  | 翻转二叉树                     | https://leetcode-cn.com/problems/invert-binary-tree/description/ | 3    |
| 96   | 验证二叉搜索树                 | https://leetcode-cn.com/problems/validate-binary-search-tree/ | 2    |
| 104  | 二叉树的最大深度               | https://leetcode-cn.com/problems/maximum-depth-of-binary-tree | 2    |
| 111  | 二叉树的最小深度               | https://leetcode-cn.com/problems/minimum-depth-of-binary-tree | 2    |
| 297  | 二叉树的序列化与反序列化       | https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/ | 2    |
| 236  | 二叉树的最近公共祖先           | https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/ | 2    |
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



## 验证二叉树

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



## 前序序列化和反序列化

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

