# 深度/广度优先搜索

| 序号 | 题目                                                 | 连接                                                         | 次数 |
| ---- | ---------------------------------------------------- | ------------------------------------------------------------ | ---- |
| 102  | 二叉树的层序遍历                                     | https://leetcode-cn.com/problems/binary-tree-level-order-traversal/#/description | 1    |
| 433  | 最小基因变化                                         | https://leetcode-cn.com/problems/minimum-genetic-mutation/#/description | 1    |
| 515  | 在每个树行中找最大值                                 | https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/#/description | 1    |
| 127  | 单词接龙                                             | https://leetcode-cn.com/problems/word-ladder/description/    | 1    |
| 126  | 单词接龙 II                                          | https://leetcode-cn.com/problems/word-ladder-ii/description/ | 1    |
| 200  | 岛屿数量                                             | https://leetcode-cn.com/problems/number-of-islands/          | 1    |
| 529  | 扫雷游戏                                             | https://leetcode-cn.com/problems/minesweeper/description/    | 1    |
| 51   | [N 皇后](https://leetcode-cn.com/problems/n-queens/) |                                                              | 1    |
|      |                                                      |                                                              |      |
|      |                                                      |                                                              |      |
|      |                                                      |                                                              |      |
|      |                                                      |                                                              |      |
|      |                                                      |                                                              |      |
|      |                                                      |                                                              |      |



## 深度优先搜索

### 代码模板

根据当前节点状态，更新状态，然后继续往下查询

```python
visited = set() 

def dfs(node, visited):
    if node in visited: # terminator
    	# already visited 
    	return 
    
	visited.add(node) 

	# process current node here. 
	for next_node in node.children(): 
		if next_node not in visited: 
			dfs(next_node, visited)
```







## 广度优先搜索

### 代码模板

第一次先把第一个元素放入队列

在每次循环清空队列时，**先保存当前队列的个数，这个就是每层的个数**

终止条件是队列中不再有其他元素

```python
def BFS(root):
    visited = set()
	queue = [] 
	queue.append([root]) 

	while queue: 
		node = queue.pop() 
		visited.add(node)

		# process(node) 
		nodes = generate_related_nodes(node) 
		queue.push(nodes)
```

