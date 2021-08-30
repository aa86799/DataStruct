package stuct.leetcode;


import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * desc  : 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *          还要 且个  update(index, v)的方法
 *
 *  https://leetcode-cn.com/problems/range-sum-query-mutable/    与303问题类似
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/12 21 33
 */
public class Leetcode307 {

    private static Integer ary[];
    public static void main(String[] args) {

        int[] nums3 = {-2, 3, 4,5};
//        NumArray numArray = new NumArray(nums3);
//        System.out.println("xx: " + numArray.sumRange(0, 0));
//        numArray.update(1,7);
//        System.out.println("xx:" + numArray.sumRange(0, 3));

        /*
         * NumArray NumArray1  分别对 获取和更新的时间复杂度
         *      前者：  o(1) o(n)
         *      后者：  o(n) o(1)
         * NumArray2 线段树解。 查询 和 更新 都是  O(logn);
         */


        NumArray2 numArray2 = new NumArray2(nums3);
        System.out.println("yy: " + numArray2.sumRange(0, 0));
        numArray2.update(1, 7);
        System.out.println("yy:" + numArray2.sumRange(0, 3));

    }

    /*
     * 声明一个数组 sums，比 nums 长度多一个；
     *  首位保存 0;
     *  之后 都保存 sums 前一位 加上  nums前一位中的 和；
     *  所以 sums[i] 保存的就是 nums 中 从 [0,i-1]的和。
     *
     *  获取区间和(i,j)：该区间值 是相对于 原始 nums 的索引区间的
     *
     */
    private static class NumArray {
        private int sums[];
        private int datas[];

        public NumArray(int[] nums) {
            sums = new int[nums.length + 1];
            datas = new int[nums.length];
            if (sums.length == 0) return;

            sums[0] = 0;
            for (int i = 1, len = sums.length; i < len; i++) {
                sums[i] = sums[i - 1] + nums[i - 1];
            }

            System.out.println(Arrays.toString(sums));

            for (int i = 0; i < nums.length; i++) {
                datas[i] = nums[i];
            }

            nums = null;
        }

        //O(1)
        public int sumRange(int i, int j) {
            return sums[j + 1] - sums[i];
        }

        //O(n)
        public void update(int index, int val) {//leetcode 307
            datas[index] = val;
            for (int i = index + 1; i < sums.length; i++) {
                sums[i] = sums[i - 1] + datas[i - 1];
            }
            System.out.println(Arrays.toString(sums));
        }
    }


    private static class NumArray1 {

        private int[] arr;
        public NumArray1(int[] nums) {
            if (nums.length == 0) return;
            this.arr = nums;
            nums = null;
        }

        //o(n)
        public int sumRange(int i, int j) {
            int sum = 0;
            for (int k = i; k <= j && k < arr.length; k++) {
                sum += arr[k];
            }
            return sum;
        }

        //o(1)
        public void update(int index, int val) {//leetcode 307
            arr[index] = val;
        }
    }

    private static class NumArray2 {

        private int[] tree;
        private int[] data;

        public NumArray2(int[] nums) {
            data = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            if(nums.length != 0) {
                tree = new int[4 * nums.length];
                builderTree(0, 0, nums.length - 1);
            }
        }

        private void builderTree( int treeIndex, int l, int r) {
            if (l == r) {
                tree[treeIndex] = data[l];
                return;
            }
            int mid = l + (r - l) / 2;
            int left = treeIndex * 2 + 1;
            int right = treeIndex * 2 + 2;
            builderTree(left, l, mid);
            builderTree(right, mid + 1, r);
            tree[treeIndex] = tree[left] + tree[right];
        }

        public int sumRange(int queryL, int queryR) {
            if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
                throw new IllegalArgumentException("Index is illegal");
            }
            return sumRange(0, 0, data.length - 1, queryL, queryR);
        }

        //O(logn)
        private int sumRange(int treeIndex, int l, int r, int quentL, int que) {
            if (quentL == l && que == r) {
                return tree[treeIndex];
            }
            int mid = l + (r - l) / 2;
            int left = treeIndex * 2 + 1;
            int right = treeIndex * 2 + 2;
            if (que <= mid) {
                return sumRange(left, l, mid, quentL, que);
            } else if (quentL >= mid + 1) {
                return sumRange(right, mid + 1, r, quentL, que);
            } else {
                int i = sumRange(left, l, mid, quentL, mid);
                int j = sumRange(right, mid + 1, r, mid + 1, que);
                return i + j;
            }
        }

        public void update(int index, int val) {
            if(index < 0 || index >=data.length){
                throw new IllegalArgumentException("index is illegal");
            }
            update(0,0,data.length-1,index,val);
        }

        //O(logn)
        private void update(int treeIndex, int l, int r, int index, int val) {
            if (l == r) {
                tree[treeIndex] = val;
                return;
            }
            int mid = l + (r - l) / 2;
            int left = treeIndex * 2 + 1;
            int right = treeIndex * 2 + 2;
            if (index >= mid + 1) {
                update(right, mid + 1, r, index, val);
            } else {
                update(left, l, mid, index, val);
            }
            tree[treeIndex] = tree[left] + tree[right];
        }
    }
}
