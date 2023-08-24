package algorithm.sort;

import java.util.Arrays;

/**
 * 计数排序
 */
public class H_CountingSort {


    public static void main(String[] args) {
        /*
         * 假设只有 8 个考生，分数在 0 到 5 分之间。这 8 个考生的成绩我们放在一个数组。
         * 0~5分，6个桶。
         */
        int[] ary = new int[]{2, 5, 3, 0, 2, 3, 0, 3};
        int[] bucket = new int[6];
        counting(ary, bucket);
        for (int i = 0; i < bucket.length; i++) {
            System.out.println(i + "分的有 " + bucket[i] + " 个");
        }
    }

    static void counting(int[] ary, int[] bucket) {
        /*for (int i = 0; i < bucket.length; i++) { // i 就是分数的 索引
//            for (int j = 0; j < ary.length; j++) {
//                if (ary[j] == i) {
//                    bucket[i]++;
//                }
//            }
            for (int j : ary) {
                if (j == i) {
                    bucket[i]++;
                }
            }
        }*/

        // 计算每个元素的个数，放入c中
        for (int i = 0; i < ary.length; ++i) {
            bucket[ary[i]]++; // ary[i] 是分数；分数是 bucket 的下标
        }
    }

    // 计数排序，a是数组，n是数组大小。假设数组中存储的都是非负整数
    static void countingSort(int[] a, int n) {
        if (n <= 1) return;
        int max = a[0]; // 最大分数
        for (int i = 1; i < n; ++i) {
            if (max < a[i]) {
                max = a[i];
            }
        }

        int[] c = new int[max + 1]; // 申请一个计数数组c，下标大小[0,max]； 分桶
        for (int i = 0; i <= max; ++i) {
            c[i] = 0;
        }

        // 计算每个元素的个数，放入c中
        for (int i = 0; i < n; ++i) {
            c[a[i]]++;
        }

        // 依次累加; 前序求和；  c[6]=[2,0,2,3,0,1] 前序求和 =>  [2,2,4,7,7,8]
        for (int i = 1; i <= max; ++i) {
            c[i] = c[i - 1] + c[i];
        }

        // 临时数组r，存储排序之后的结果
        int[] r = new int[n];
        // 计算排序的关键步骤，有点难理解
        for (int i = n - 1; i >= 0; --i) {
            int index = c[a[i]] - 1;
            r[index] = a[i];
            c[a[i]]--; // 结果中赋值一个，分数数组中就减少一个
        }

        // 将结果拷贝给a数组
        for (int i = 0; i < n; ++i) {
            a[i] = r[i];
        }
    }
}
