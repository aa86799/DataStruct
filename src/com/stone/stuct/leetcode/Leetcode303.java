package com.stone.stuct.leetcode;

import com.stone.stuct.segmenttree.Merger;
import com.stone.stuct.segmenttree.SegmentTree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * desc  : 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *          用线段树区间查询解决
 *  https://leetcode-cn.com/problems/range-sum-query-immutable/   303
 *
 *  https://leetcode-cn.com/problems/range-sum-query-mutable/      307
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/12 21 33
 */
public class Leetcode303 {
    /*
    示例：

        给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()

        sumRange(0, 2) -> 1
        sumRange(2, 5) -> -1
        sumRange(0, 5) -> -3
     */

    private static Integer ary[];
    public static void main(String[] args) {
        Integer[] nums = new Integer[1008];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        SegmentTree<Integer> tree = new SegmentTree<>(nums, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a + b;
            }
        });
        //线段树解, 效率第二，但比普通方式强很多
        System.out.println("--------------------");
        System.out.println(tree.query(0,20));
        System.out.println(tree.query(2,50));
        System.out.println(tree.query(0,1007));

        //普通循环求和 解，效率最差
        System.out.println("--------------------");
        System.out.println(sumRange(nums,0,20));
        System.out.println(sumRange(nums,2,50));
        System.out.println(sumRange(nums,0,1007));

        //用新数组存前 n 个元素和的方式，效率最高
        System.out.println("--------------------");
        int[] nums2 = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums2);
        System.out.println(numArray.sumRange(0,2));
        System.out.println(numArray.sumRange(2,5));
        System.out.println(numArray.sumRange(0,5));

    }

    private static int sumRange(Integer[] nums, int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += nums[k];
        }
        return sum;
    }

    /*
     * 声明一个数组 sums；
     *  首位保存 nums 的首位值；
     *  之后 都保存 sums 前一位 加上 当前 nums中的 和；
     *  所以 sums[i] 保存的就是 nums 中 从 [0,i]的和。
     *
     *  获取区间和(i,j)：
     *      1. 从i=0开始获取， 即就是获取sums[j] 位的值
     *      2. 从 i非0开始获取， sums[i]包括[0,i]的和，sums[j]包括[0,j]的和;
     *              要求 [i,j]的，即 i 不能被包含；所以用 sums[j] - sums[i-1]
     */
    private static class NumArray {
        private int sums[];
        public NumArray(int[] nums) {
            if (nums.length == 0) return;
            sums = new int[nums.length];

            sums[0] = nums[0];
            for (int i = 1, len = sums.length; i < len; i++) {
                sums[i] = sums[i - 1] + nums[i];
            }

            System.out.println(Arrays.toString(sums));

            nums = null;
        }

        public int sumRange(int i, int j) {
            if (i == 0) {
                return sums[j];
            }
            return sums[j] - sums[i - 1];
        }
    }

    /*
     * sums 多保存了一个 首位为0的值。
     */
    private static class NumArray1 {
        ArrayList<Integer> sums;
        public NumArray1(int[] nums) {
            if (nums.length == 0) return;

            sums = new ArrayList<>();
            sums.add( 0);
            for (int i = 1, len = nums.length  + 1; i < len; i++) {
                sums.add(sums.get(i - 1) + nums[i - 1]);
            }

            nums = null;
        }

        public int sumRange(int i, int j) {
            return sums.get(j + 1) - sums.get(i);
        }
    }


}
//
//class NumArray {
//
//    private int ary[];
//    public NumArray(int[] nums) {
//        ary = nums;
//        nums = null;
//    }
//
//    public int sumRange(int i, int j) {
//        int sum = 0;
//        for (int k = i; k <= j; k++) {
//            sum += ary[k];
//        }
//        return sum;
//    }
//}