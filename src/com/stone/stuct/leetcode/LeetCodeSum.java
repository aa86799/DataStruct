package com.stone.stuct.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/23 19 07
 */
public class LeetCodeSum {

/*
    给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，
    并返回他们的数组下标。

    你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

    示例:

    给定 nums = [2, 7, 11, 15], target = 9

    因为 nums[0] + nums[1] = 2 + 7 = 9
    所以返回 [0, 1]
*/

    // O(n^2)
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0, len = nums.length; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {//最慢
        int len = nums.length;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            list.add(nums[i]);
        }

        for (int i = 0; i < len; i++) {
            int another = target - nums[i];
            if (list.contains(another)) {
                int index = list.indexOf(another);
                if (index != i)
                    return new int[]{i, index};
            }
        }
        return null;
    }

    public static int[] twoSum3(int[] nums, int target) {//最快
        int len = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < len; i++) {
            int another = target - nums[i];
            if (map.keySet().contains(another)) {
                int index = map.get(another);
                if (index != i)
                    return new int[]{i, index};
            }
        }
        return null;
    }

    public static void main(String[] args) {
//        int[] nums = {2, 7, 11, 15};
        int[] nums = {3,3};
        int target = 6;
//        System.out.println(Arrays.toString(twoSum(nums, target)));
//        System.out.println(Arrays.toString(twoSum2(nums, target)));
        System.out.println(Arrays.toString(twoSum3(nums, target)));
    }
}
