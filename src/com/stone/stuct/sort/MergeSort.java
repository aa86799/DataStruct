package com.stone.stuct.sort;


import java.util.Arrays;

/**
 * desc  : 归并排序
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/1/6 15 09
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] ary = new int[]{4, 8, 7, 6, 5, 3, 2, 1, 9};
//        sort1(ary);
        mergeSort(ary);
        System.out.println(Arrays.toString(ary));
    }

    public static void sort(int[] ary) {
        int[] temp = new int[ary.length];
        sort(ary, 0, ary.length - 1);
    }

    private static void sort(int[] ary, int left, int right) {
        if (left < right) {
            int mid = (left + right) >> 1;
            sort(ary, left, mid);
            sort(ary, mid + 1, right);
            merge(ary, left, mid, right);
        }
    }
    //两两归并排好序的数组（2路归并）
    private static void merge(int[] ary, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int t = 0;//临时数组索引
        int[] temp = new int[right - left + 1];
        // 把较小的数先移到新数组中
        while (i <= mid && j <= right) {
            if (ary[i] <= ary[j]) {
                temp[t++] = ary[i++];
            } else {
                temp[t++] = ary[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[t++] = ary[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= right) {
            temp[t++] = ary[j++];
        }
//        t = 0;
        // 把新数组中的数覆盖原数组
//        while (left <= right) {
//            ary[left++] = temp[t++];
//        }
        //可以优化成下面的写法
        System.arraycopy(temp, 0, ary, left, /*right - left + 1*/ temp.length);
        System.out.println("merge sort1: " + Arrays.toString(ary));
    }

    public static void mergeSort(int[] ary) {
        for (int i = 1; i < ary.length; i*=2) {
            for (int j = 0; j < ary.length; j+=i*2) {
                merge(ary, j, Math.min(j + i - 1, ary.length - 1), Math.min(j + 2 * i - 1, ary.length - 1));
            }
        }
    }
}
