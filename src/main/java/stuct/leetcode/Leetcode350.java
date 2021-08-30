package stuct.leetcode;

import java.util.*;

/**
 * desc  : 两个数组的交集。
 *
 *      输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 *      即 结果集中的元素出现次数 等于 在两个数组中 共同出现的 最小次数
 * <p> https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/10 21 52
 */
public class Leetcode350 {

    public static void main(String[] args) {
//        int nums1[] = {1, 2, 2, 1, 3, 2, 3, 1};
//        int nums2[] = {2, 2, 3, 1, 3, 1, 3, 2, 1, 2, 3};

        int nums1[] = {4, 9, 5, 9};
        int nums2[] = {9, 4, 9, 8, 5, 5};

//        int result[] = intersection(nums1, nums2);
        int result[] = intersection2(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }

    /*
     * 用映射的 集合实现
     */
    public static int[] intersection(int[] nums1, int[] nums2) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num)) {
                list.add(num);
                map.put(num, map.get(num) - 1);
                if (map.get(num) == 0) {
                    map.remove(num);
                }
            }
        }

        int[] results = new int[list.size()];
        for (int i = 0, len = list.size(); i < len; i++) {
            results[i] = list.get(i);
        }
        return results;
    }

    /*
     * 先排序;
     * 同时遍历，元素相等添加
     */
    public static int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); //aes
        Arrays.sort(nums2); //aes

        ArrayList<Integer> list = new ArrayList<>();

        int i = 0, j = 0;
        int len1 = nums1.length, len2 = nums2.length;
        while (i < len1 && j < len2) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] results = new int[list.size()];
        for (int k = 0, len = list.size(); k < len; k++) {
            results[k] = list.get(k);
        }
        return results;
    }
}
