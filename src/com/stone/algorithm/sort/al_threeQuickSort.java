package com.stone.algorithm.sort;

import java.util.Arrays;

/*
 * https://leetcode-cn.com/problems/sort-colors/
 * 使用整数 0、 1 和 2 分别表示红色、白色和蓝色。对随机的颜色排序
 */
public class al_threeQuickSort {

    public static void main(String[] args) {
        int[] ary1 = {2, 0, 2, 1, 1, 0, 1, 0, 2, 2, 1, 2, 0, 2, 1, 1, 0};
        int[] ary2 = ary1.clone();

        sort1(ary1);
        System.out.println(Arrays.toString(ary1));

        sort2(ary2);
        System.out.println(Arrays.toString(ary2));
    }

    /*
     * 先找出每个颜色的个数，再分别循环重置
     * O(2n)
     */
    private static void sort1(int[] ary) {
        long startTime = System.nanoTime();

        int[] eSizeAry = new int[3];
        for (int value : ary) {
            eSizeAry[value]++;
        }
        int index = 0;
        for (int i = 0; i < eSizeAry[0]; i++) {
            ary[index++] = 0;
        }
        for (int i = 0; i < eSizeAry[1]; i++) {
            ary[index++] = 1;
        }
        for (int i = 0; i < eSizeAry[2]; i++) {
            ary[index++] = 2;
        }
        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

    /*
     * 利用三路快速排序思想
     * 一次循环解决
     * O(n)
     */
    private static void sort2(int[] ary) {
        long startTime = System.nanoTime();

        if (ary.length == 1) return;
        int zeroIndex = 0; //[0, zeroIndex] 之间都是0
        int twoIndex = ary.length - 1; //[twoIndex, length-1] 之间都是2
        for (int i = 0; i <= twoIndex; ) {
            if (ary[i] == 0) {
                Util.swap(ary, zeroIndex, i);
                zeroIndex++;
                i++;
            } else if (ary[i] == 1) {
                i++;
            } else {
                Util.swap(ary, i, twoIndex);
                twoIndex--;
            }
        }

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

}
