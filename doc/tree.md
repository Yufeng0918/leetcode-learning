# 树

| 序号 | 题目                           | 连接                                                         | 次数 |
| ---- | ------------------------------ | ------------------------------------------------------------ | ---- |
| 94   | Binary Tree Inorder Traversal  | https://leetcode-cn.com/problems/binary-tree-inorder-traversal/ | 1    |
| 144  | Binary Tree Preorder Traversal | https://leetcode-cn.com/problems/binary-tree-preorder-traversal/ | 1    |
| 590  | N-ary Tree Postorder Traversal | https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/ | 1    |
| 589  | N-ary Tree Preorder Traversal] | https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/ | 1    |



## 中序遍历

```JAVA
public void traverse(TreeNode node, List<Integer> result) {
        
        if (node.left != null) traverse(node.left, result);

        result.add(node.val);

        if (node.right != null) traverse(node.right, result);
}

```



## 前序遍历

```JAVA
public void traverse(TreeNode node, List<Integer> result) {

        result.add(node.val);

        if (node.left != null) traverse(node.left, result);

        if (node.right != null) traverse(node.right, result);

}
```

