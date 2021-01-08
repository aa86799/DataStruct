package com.stone.algorithm.sort;

import java.util.Arrays;

/**
 * desc  : 求集合中的逆序对，用归并算法
 *
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/25 22 25
 */
public class al_InversionPair {
    private int inverseCount;

    public static void main(String[] args) {
//        int[] ary = {2,8,9,3,4,6,5};
//        int[] ary = {3,2,4,6,5,8};
//        int[] ary = Util.buildRandomArray(10);  //有重复数据有问题
        int[] ary = Util.buildRandomArraySet(6);

        System.out.println(Arrays.toString(ary));
        al_InversionPair inversionPair = new al_InversionPair();
        inversionPair.mergeSort(ary, 0, ary.length - 1);
        System.out.println(inversionPair.inverseCount);

//        [8, 2, 3, 5, 8, 4, 1, 6, 7, 1]
        //8+2+2+3+5+2+1=
    }
    /*
     *   [3, 2, 4, 6, 5, 8]，最终要求顺序排序，
     *      逆序对： 3-2；  6-5；这么多对
     *   使用归并排序解决：
     *      在归并过程中，左右子序 都是有序的。
     *      且在左边的最大值， 不比右边最小值小时
     *       [2,5,9] [3, 4, 6]
     *       归并条件中 res[i] > res[j]时，这时
     *          i 及以后的左边元素 都是 j 构成逆序对
     *
     */

    //在数量级较小时，使用插入排序，效果更高。 是对 sort1的优化
    private void mergeSort(int[] ary, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort(ary, start, mid);
        mergeSort(ary, mid + 1, end);

        //直接归并
//        merge(ary, start, mid, end);

        //先比较左右子序，左边的最大值 比 右边的最小值
        if (ary[mid] > ary[mid + 1]) {//[start,end]是否是降序的，是就调用 merge 合并成升序
            merge(ary, start, mid, end);
        }
    }

    private void merge(int[] ary, int start, int mid, int end) {
        //声明临时数组，存储原数组中 [start,end]的数据
        int[] res = new int[end - start + 1];
        for (int i = start; i <= end; i++) {
            res[i - start] = ary[i];
        }

        int i = start; //左序列开始位置
        int j = mid + 1;//右序列开始位置
        int k = start; //原序列中开始位置
        while (k <= end) {
            if (j > end) {//右边序列访问完了
                ary[k] = res[i - start];
                i++;
            } else if (i > mid) {//左边序列访问完了
                ary[k] = res[j - start];
                j++;
            } else if (res[i - start] < res[j - start]) {//左边小于右边
                ary[k] = res[i - start];
                i++;
            } else if (res[i - start] > res[j - start]) {//左边大于右边
                ary[k] = res[j - start];
                j++;
                inverseCount += mid - i + 1; //对于有数值重复的元素的序列是不对的。
                // 比如左边 [i]=8， 右边[j,j+1]=7,8;
            }

            k++;
        }
    }
}
