package com.yufeng.leetcode001;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SolutionTest {

    private static  Solution solution;

    @BeforeClass
    public static void init(){
        solution = new Solution();
    }

    @Test
    public void testArray1() {

        int target = 9;
        int[] nums = {2, 7, 11, 15};
        int[] arrays = solution.twoSum(nums, target);
        Assert.assertEquals(target, nums[arrays[0]] + nums[arrays[1]]);
    }

}
