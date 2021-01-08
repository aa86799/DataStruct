package com.stone.stuct.leetcode;

import java.util.*;

/**
 * desc  : 两个数组的交集。交集数组中，不包含重复的 元素。
 *          一个个集合set，去重 数组1;
 *          定义一个结果集，遍历2，若1中有，则加入到结果集中。
 * <p>
 * <p> https://leetcode-cn.com/problems/intersection-of-two-arrays/submissions/
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/10 21 52
 */
public class Leetcode349 {

    public static void main(String[] args) {
        int nums1[] = {1, 2, 2, 1, 3, 2, 3, 1};
        int nums2[] = {2, 2, 3, 1, 3, 1, 3, 2, 1, 2, 3};

//        int result[] = intersection(nums1, nums2);
//        int result[] = intersection2(nums1, nums2);
        int result[] = intersection3(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        HashSet<Integer> set2 = new HashSet<>();
        for (int num : nums2) {
            set2.add(num);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int num : set2) {
            if (set1.contains(num)) {
                list.add(num);
            }
        }

        int[] results = new int[list.size()];
        for (int i = 0, len = list.size(); i < len; i++) {
            results[i] = list.get(i);
        }
        return results;

    }

    /*
     * nums1 长度较大；
     */
    private static int[] intersection2(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        //O(n^2) 遍历，太慢
        for (int aNums2 : nums2) {
            for (int aNums1 : nums1) {
                if (aNums2 == aNums1) {
                    set.add(aNums2);
                }
            }
        }
        int[] results = new int[set.size()];
        int index = 0;
        for (Integer num : set) {
            results[index++] = num;
        }
        return results;
    }

    private static int[] intersection3(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); //aes
        Arrays.sort(nums2); //aes

        HashSet<Integer> set = new HashSet<>();

        int i = 0, j = 0;
        int len1 = nums1.length, len2 = nums2.length;
        while (i < len1 && j < len2) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] results = new int[set.size()];
        int index = 0;
        for (int num : set) {
            results[index++] = num;
        }
        return results;
    }
}
