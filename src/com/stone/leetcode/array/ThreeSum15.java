package com.stone.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * desc:    https://leetcode-cn.com/problems/3sum     [15]三数之和
 * author:  stone
 * email:   aa86799@163.com
 * blog :   https://stone.blog.csdn.net
 * time:    2020/9/6 00:46
 */
public class ThreeSum15 {

    public static void main(String[] args) {
        List<List<Integer>> result = new ThreeSum15().threeSum(new int[] {-1,0,1,2,-1,-4,-2,-3,3,0,4});
        System.out.println(result);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // a+b+c=0 ==>  a+b=-c
        int k = 0; //从左向右
        int i = k + 1;
        int j = nums.length - 1;

//        List<Integer> list = new ArrayList<>();
//        list.add(nums[i]);
//        list.add(nums[j]);
//        list.add(nums[k]);
        Arrays.sort(nums);
        while (k < nums.length - 2) {
            if (-nums[k] < nums[i] + nums[j]) {
                k++;
                i = k + 1;
            } else if (-nums[k] > nums[i] + nums[j]) {
                j--;
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                list.add(nums[j]);
                list.add(nums[k]);
                result.add(list);
                while (i < j && nums[i] == nums[i+1]) {
                    i++;
                }
                while (i < j && nums[j] == nums[j-1]) {
                    j--;
                }
//                i++;
//                j--;
            }

            if (i>=j) {
                k++;
                i=k+1;
                j=nums.length - 1;
            }
        }

        return result;
    }


}
