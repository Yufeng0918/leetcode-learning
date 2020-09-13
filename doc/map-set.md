# 哈希映射/哈希集合

| 序号 | 题目             | 连接                                                  | 次数 |
| ---- | ---------------- | ----------------------------------------------------- | ---- |
| 242  | 有效的字母异位词 | https://leetcode-cn.com/problems/valid-anagram/       | 2    |
| 49   | 字母异位词分组   | https://leetcode-cn.com/problems/group-anagrams/      | 2    |
| 1    | 两数之和         | https://leetcode-cn.com/problems/two-sum/submissions/ | 2    |



## 应用

#### 布隆过滤器 BloomFilter

布隆过滤器可以**近乎准确地快速判断一个元素是否在集合里面**，核心实现是一个超大的位数组和几个哈希函数。**假设位数组的长度为m，哈希函数的个数为k**。假设集合里面有3个元素{x, y, z}，哈希函数的个数为3。首先将位数组进行初始化，将里面每个位都设置位0。

**插入**：对于集合里面的每一个元素，**将元素依次通过k个哈希函数进行映射，每次映射都会产生一个哈希值，这个值对应位数组上面的一个点**，然后将位数组对应的位置标记为1。

**查询**：W元素是否存在集合中的时候，同样的方法将W通过哈希映射到位数组上的k个点。

+ 如果k个点的其中有一个点不为1，则可以判断该元素一定不存在集合中。

+ 如果k个点都为1，则该元素可能存在集合中。

![](../images/leetcode-10.jpg)





## 题解

#### 有效异位词

异位词 = 相同字符串的排列

+ 排序
+ 哈希映射记录每个字符的次数

```JAVA
class Solution {
    public boolean isAnagram(String s, String t) {

        if (s == null && t == null) return true;

        if (s.length() != t.length()) return false;
        
        Map<Character, Integer> map1 = new HashMap<>();
        for(Character c: s.toCharArray()) {
            putIntoMap(map1, c);
        }

        Map<Character, Integer> map2 = new HashMap<>();
        for(Character c: t.toCharArray()) {
            putIntoMap(map2, c);
        }


        for(Character c: map1.keySet()) {
            if (!map1.get(c).equals(map2.get(c))) return false;
        }

        return true;
    }

    public static void putIntoMap(Map<Character, Integer> map, Character c) {

        if (map.containsKey(c)) {
            map.put(c, map.get(c) + 1);
        } else {
            map.put(c, 1);
        }
    }
}
```



#### 字母异位词分组

利用异位词分组是异位词排序以后字符串相投

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> result = new LinkedList<>();

        for(int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
        
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<String>());
            }
            map.get(sorted).add(str);
        }


        for(String key: map.keySet()) {
            result.add(map.get(key));
        }

        return result;
    }
}
```



#### 两数之和

```JAVA
class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            
            int rest = target - nums[i];
            if (map.containsKey(rest)) {
                return new int[]{map.get(rest), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[2];
    }
}
```

