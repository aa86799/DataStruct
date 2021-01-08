package com.stone.algorithm.sort;

import java.util.*;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/23 19 39
 */
public class Util {

    public static int[] buildRandomArray(int count) {
        Random random = new Random();
        int[] ary = new int[count];
        for (int i = 0; i < count; i++) {
            ary[i] = random.nextInt(count);
        }
        return ary;
    }

    //构建随机不重复的数组
    public static int[] buildRandomArraySet(int count) {
        int[] ary = new int[count];
        for (int i = 0; i < count; i++) {
            ary[i] = i;
        }
        //随机交换位置，交换一半的次数
        Random random = new Random();
        for (int i = 0; i < count / 2; i++) {
            swap(ary, i, random.nextInt(count));
        }
        return ary;
    }

    public static int[] buildAscArray(int count) {
        int[] ary = new int[count];
        for (int i = 0; i < count; i++) {
            ary[i] = i;
        }
        return ary;
    }

    public static int[] buildDescArray(int count) {
        int[] ary = new int[count];
//        for (int i = count - 1; i >= 0; i--) {
//            ary[count - 1 - i] = i;
//        }
        for (int i = 0; i < count; i++) {
            ary[count - 1 - i] = i;
        }
        return ary;
    }

    //构建局部顺序，其它随机
    public static int[] buildPartAsc(int count, int serialCount) {
        int[] ary = new int[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            if (i < serialCount) {
                ary[i] = i;
            } else {
                ary[i] = random.nextInt(count);
            }
        }
        return ary;
    }

    //构建局部倒序，其它随机
    public static int[] buildPartDesc(int count, int serialCount) {
        int[] ary = new int[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            if (i < serialCount) {
                ary[count - 1 - i] = i;
            } else {
                ary[count - 1 - i] = random.nextInt(count);
            }
        }
        return ary;
    }

    //构建先升后降
    public static int[] buildAscDesc(int count) {
        int[] ary = new int[count];
        for (int i = 0; i < count; i++) {
            if (i < count / 2) {
                ary[i] = i;
            } else {
                ary[i] = count / 2 - i % (count / 2);// 偶数个 count 需要是
            }
        }
        return ary;
    }

    //构建先降后升


    public static boolean isAsc(int[] ary) {
        for (int i = 1; i < ary.length; i++) {
            if (ary[i] < ary[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDesc(int[] ary) {
        for (int i = 1; i < ary.length; i++) {
            if (ary[i] > ary[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void swap(int[] ary, int i, int j) {
//        int temp = ary[i];
//        ary[i] = ary[j];
//        ary[j] = temp;
        /*
         *
         *  异或操作，当 i j 索引位相同时，会有问题
         */
        if ( i == j) return;
        ary[i] = ary[i] ^ ary[j];
        ary[j] = ary[i] ^ ary[j];
        ary[i] = ary[i] ^ ary[j];
    }

    public static void insertSort(int[] ary, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int e = ary[i];
            int j; //保存元素 e 应该插入的位置
            //前 j 个元素间，满足条件，才交换
            for (j = i; j > start && e < ary[j - 1]; j--) {
                ary[j] = ary[j - 1];//大的值后移
            }
            ary[j] = e;
        }
    }
}
