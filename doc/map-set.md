# 哈希映射/哈希集合

| 序号 | 题目             | 连接                                                  | 次数 |
| ---- | ---------------- | ----------------------------------------------------- | ---- |
| 242  | 有效的字母异位词 | https://leetcode-cn.com/problems/valid-anagram/       | 2    |
| 49   | 字母异位词分组   | https://leetcode-cn.com/problems/group-anagrams/      | 2    |
| 1    | 两数之和         | https://leetcode-cn.com/problems/two-sum/submissions/ | 2    |



## 有效异位词

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



## 字母异位词分组

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



## 两数之和

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

