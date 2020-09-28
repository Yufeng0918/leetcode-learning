package com.yufeng.hackerRank2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Solution {

    public static int stockPairs(List<Integer> stocksProfit, long target) {
        // Write your code here

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < stocksProfit.size(); i++) {
            map.put(stocksProfit.get(i), map.getOrDefault(stocksProfit.get(i), 0) + 1);
        }

        int result = 0;
        List<int[]> my = new ArrayList<>();
        for (Integer i : map.keySet()) {

            if (map.get(i).compareTo(0) <= 0) {
                continue;
            }

            if (i >= target) {
                continue;
            }

            Integer j = Long.valueOf(target - i).intValue();

            if (map.containsKey(j) && map.get(j).compareTo(0) >= 1) {
                if (i.compareTo(j)  != 0) {
                    map.put(i, map.get(i) - 1);
                    map.put(j, map.get(j) - 1);
                    my.add(new int[]{i, j});
                    result++;
                } else {
                    if (map.get(i) >= 2) {
                        map.put(i, map.get(i) - 2);
                        my.add(new int[]{i, i});
                        result++;
                    }
                }
            }
        }
        my.sort((arr1, arr2) -> arr1[0] - arr2[0]);
        my.forEach(i -> System.out.println(i[0] + " " + i[1]));


        return result;
    }

    public static void main(String[] args) throws Exception{

        File file = new File("input.txt");
        System.out.println(file.exists());

        FileReader fr=new FileReader(file);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
        String line = br.readLine();

        Integer i = Integer.valueOf(line);
        List<Integer> list = new ArrayList<>(i);

        int n = 0;
        while(n < i) {
            list.add(Integer.valueOf(br.readLine()));
            n++;
        }

        Integer target = Integer.valueOf(br.readLine());

//        System.out.println(stockPairs(Arrays.asList(6, 12, 3, 9, 3, 5, 1), 12));
        System.out.println(stockPairs(list, target));
    }
}
