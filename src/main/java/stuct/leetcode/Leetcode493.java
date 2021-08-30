package stuct.leetcode;

/**
 * desc  : 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
            你需要返回给定数组中的重要翻转对的数量

 *  https://leetcode-cn.com/problems/reverse-pairs/
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/13 01 20
 */
public class Leetcode493 {

    public static void main(String[] args) {
//        int[] nums = {1,3,-2, 0, 3,-3,1};
        int[] nums = {2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
//        int[] nums = {-5,-5};
        System.out.println(reversePairs(nums));

        System.out.println(Integer.MAX_VALUE);


        /*用归并排序*/
    }


    //要考虑不越界的问题， 整数的最小边界和最大边界， 但以双层循环 效率太慢，测试用例太多，会卡死
    public static int reversePairs(int[] nums) {
        int sum = 0;
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] > (long)2 * nums[j]) { //2倍 可能越界，要升成 long
                    sum++;
                }
            }
        }
        return sum;
    }

}
