# 广度优先搜索



| 序号 | 题目                 | 连接                                                         | 次数 |
| ---- | -------------------- | ------------------------------------------------------------ | ---- |
| 102  | 二叉树的层序遍历     | https://leetcode-cn.com/problems/binary-tree-level-order-traversal/#/description | 1    |
| 433  | 最小基因变化         | https://leetcode-cn.com/problems/minimum-genetic-mutation/#/description | 1    |
| 515  | 在每个树行中找最大值 | https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/#/description | 1    |
| 127  | 单词接龙             | https://leetcode-cn.com/problems/word-ladder/description/    | 1    |
| 126  | 单词接龙 II          | https://leetcode-cn.com/problems/word-ladder-ii/description/ | 1    |
| 200  | 岛屿数量             | https://leetcode-cn.com/problems/number-of-islands/          | 1    |
| 529  | 扫雷游戏             | https://leetcode-cn.com/problems/minesweeper/description/    | 1    |



## 代码模板

第一次先把第一个元素放入队列

在每次循环清空队列时，**先保存当前队列的个数，这个就是每层的个数**

终止条件是队列中不再有其他元素

```JAVA
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> allResults = new ArrayList<>();
    if (root == null) {
        return allResults;
    }
    Queue<TreeNode> nodes = new LinkedList<>();
    nodes.add(root);
    while (!nodes.isEmpty()) {
      
        // save current queue size
        int size = nodes.size();
        List<Integer> results = new ArrayList<>();
        // fetch all item in current level
        for (int i = 0; i < size; i++) {
            TreeNode node = nodes.poll();
            results.add(node.val);
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
        allResults.add(results);
    }
    return allResults;
}
```

