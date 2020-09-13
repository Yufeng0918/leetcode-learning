# 深度/广度优先搜索

| 序号 | 题目                                                         | 连接                                                         | 次数 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- |
| 102  | 二叉树的层序遍历                                             | https://leetcode-cn.com/problems/binary-tree-level-order-traversal/#/description | 1    |
| 433  | [最小基因变化](https://leetcode-cn.com/problems/minimum-genetic-mutation/) |                                                              | 1    |
| 515  | 在每个树行中找最大值                                         | https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/#/description | 1    |
| 127  | [单词接龙](https://leetcode-cn.com/problems/word-ladder/)    |                                                              | 1    |
| 126  | 单词接龙 II                                                  | https://leetcode-cn.com/problems/word-ladder-ii/description/ | 1    |
| 200  | 岛屿数量                                                     | https://leetcode-cn.com/problems/number-of-islands/          | 1    |
| 529  | 扫雷游戏                                                     | https://leetcode-cn.com/problems/minesweeper/description/    | 1    |
| 51   | [N 皇后](https://leetcode-cn.com/problems/n-queens/)         |                                                              | 2    |
| 36   | [有效的数独](https://leetcode-cn.com/problems/valid-sudoku/) |                                                              | 2    |
| 37   | [解数独](https://leetcode-cn.com/problems/sudoku-solver/)    |                                                              | 2    |
| 1091 | [二进制矩阵中的最短路径](https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/) |                                                              |      |
|      |                                                              |                                                              |      |
|      |                                                              |                                                              |      |
|      |                                                              |                                                              |      |



## 深度优先搜索  DFS

### 概述

Depth-First-Search最直观的例子就是“走迷宫”。深度优先搜索用回溯思想

深度优先搜索算法每条边最多会被访问两次，一次是遍历，一次是回退。所以，深度优先搜索算法的**时间复杂度是 O(E)。**

广度优先搜索的空间消耗主要在变量visited和调用栈，**空间复杂度是 O(V)。**

### 实现

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





## 广度优先搜索 BFS

### 概述

Breadth-First-Search就是一种“地毯式”**层层推进的搜索策略**，即先查找离起始顶点最近的，然后是次近的，依次往外搜索。

广度优先搜索的时间复杂度是 O(V+E)。V 表示顶点的个数，E 表示边的个数。对于一个连通图来说，E 肯定要大于等于 V-1(E等于V-1的时候，图是一条直线)，所以，**广度优先搜索的时间复杂度也可以简写为 O(E)**。

广度优先搜索的空间消耗主要在变量visited和queue，**空间复杂度是 O(V)。**

### 实现

#### 单向搜索

+ 第一次先把第一个元素放入队列
+ 在每次循环清空队列时，**先保存当前队列的个数，这个就是每层的个数**
+ 终止条件是队列中不再有其他元素

```java
int BFS(Node start, Node target) {
    Queue<Node> q; // 核心数据结构
    Set<Node> visited; // 避免走回头路
    
    q.offer(start); // 将起点加入队列
    visited.add(start);
    int step = 0; // 记录扩散的步数

    while (q not empty) {
        int sz = q.size();
        /* 将当前队列中的所有节点向四周扩散 */
        for (int i = 0; i < sz; i++) {
            Node cur = q.poll();
            /* 划重点：这里判断是否到达终点 */
            if (cur.equals(target))
                return step;
            /* 将 cur 的相邻节点加入队列 */
            for (Node x : cur.adj())
                if (!visited.contains(x)) {
                    q.offer(x);
                    visited.add(x);
                }
        }
        /* 划重点：更新步数在这里 */
        step++;
    }
}
```



#### 双向搜索

+ 必须知道起点和终点
+ 同时从起点和终点扩散，从较小的集合扩散

```java
public int doubleBFS(Node beginNode, Node endNode) {

  // 初始化起点合集
	Set<Node> start = new HashSet<>();
  start.add(beginNode);
  
  // 初始化终点合集
  Set<Node> end = new HashSet<>();
  end.add(endNode);

  // 初始化访问合集
  Set<Node> visited = new HashSet<>();
  visited.add(beginNode);
  visited.add(endNode);

 	int step = 1;
 	while(start.size() != 0 && end.size() != 0) {

    Set<Node> temp = new HashSet<>();
    // 遍历所有起点集合节点
    for(Node node1: start) {
    		
      	// 查看每个节点里面的周围节点
      	for (Node node2: node1.adj()) {
        		/*重点： 如果周围节点有终止结合， 那么停止循环，说明两个结合相邻*/
            if (end.contains(node2)) return step + 1;
          	
            /*重点：如果没有，则加入零时集合和已访问集合*/
            temp.add(node2);
          	visited.add(node2);
        }
    }

    step++;
    
    /*重点：把零时集合赋值给起始集合并查看是否需要交换*/
    start = temp;
    if (start.size() > end.size()) {
    	temp = start;
    	start = end;
    	end = temp;
    }
  }
  return -1;
}
```



