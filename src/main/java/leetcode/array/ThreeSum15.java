package leetcode.array;

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
//        List<List<Integer>> result = new ThreeSum15().threeSum(new int[] {-1,0,1,2,-1,-4,-2,-3,3,0,4});
//        List<List<Integer>> result = new ThreeSum15().threeSum(new int[] {-4, 3, 1});
//        List<List<Integer>> result = new ThreeSum15().threeSum(new int[] {-1,0,1,2,-1,-4});
        List<List<Integer>> result = new ThreeSum15().threeSum(new int[] {-1,0,1,2,-1,-4});
        System.out.println(result);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) return null;
        if (nums.length < 3) return new ArrayList<List<Integer>>(0);
        Arrays.sort(nums);
        if (nums[0] > 0) return new ArrayList<List<Integer>>(0);
        //排序后的数组首元素大于0，以及元素数量小于3时不成立
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>(n-2);

        /*
         * a+b+c=0  => -a=b+c
         * i,j,k 指针。 i 从左向右， j从右向左； 排序后，-[k] = [i] + [j];
         * k 从0开始； i 从 k+1 开始；j 从 n-1开始； k 最大索引位置是 n-3;
         * 判断，移动指针；
         * 考虑重复问题，要跳过；
         * 当 i和j相遇，说明没找到，k要递增；k递增后，重复 "i 从 k+1 开始；j 从 n-1开始"
         */

        int k=0, i=k+1, j=n-1;
        while(k < n-2) {
            // 肯定不满足 -[k]=[i]+[j], [j]>=[i]>=[k], 又因此时 [i] [j] 也是>0
            if (nums[k] > 0) return result;
            // 去重复结果
            if (k > 0 && nums[k] == nums[k-1]) {
                k++;
                i=k+1;
                j=n-1;
                continue;
            }

            if (-nums[k] < nums[i] + nums[j]) {
                // 因升序排序后，nums[j] > nums[i]的，j 较大，要递减
                j--;
            } else if (-nums[k] > nums[i] + nums[j]) {
                i++;
            } else {
                List<Integer> subResult = new ArrayList<Integer>(3);
                subResult.add(nums[k]);
                subResult.add(nums[i]);
                subResult.add(nums[j]);
                result.add(subResult);
                // i++;  j--; 考虑重复问题, 考虑i j 指针有效问题
                while (i < j && nums[i] == nums[i + 1]) i++;
                while (i < j && nums[j] == nums[j - 1]) j--;
                // 再移动一次，跳过重复的
                i++;
                j--;
            }
            // k++; 当 i和j相遇，说明没找到，k要递增；k递增后，重复 "i 从 k+1 开始；j 从 n-1开始"
            if (i >= j) {
                k++;
                i=k+1;
                j=n-1;
            }
        }

        return result;
    }


}
