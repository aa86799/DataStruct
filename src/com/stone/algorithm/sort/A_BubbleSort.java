package com.stone.algorithm.sort;

import java.util.Arrays;

/**
 * desc  : 冒泡排序  O(n^2)
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/23 19 38
 */
public class A_BubbleSort {

    public static void main(String[] args) {
//        int[] ary = Util.buildRandomArray(1000 * 100/5);
        int[] ary = Util.buildDescArray(1000 * 100/4);
//        System.out.println(Arrays.toString(ary));
        int[] ary2 = ary.clone();
        int[] ary3 = ary.clone();
        int[] ary4 = ary.clone();
        int[] ary5 = ary.clone();
        int[] ary6 = ary.clone();

        A_BubbleSort bubbleSort = new A_BubbleSort();
        bubbleSort.sort1(ary);
        bubbleSort.sort2(ary2);
        bubbleSort.sort3(ary3);
        bubbleSort.sort4(ary4);
        bubbleSort.sort5(ary5);
        bubbleSort.sort6(ary6);

        System.out.println(Util.isAsc(ary));
        System.out.println(Util.isAsc(ary2));
        System.out.println(Util.isAsc(ary3));
        System.out.println(Util.isAsc(ary4));
        System.out.println(Util.isAsc(ary5));
        System.out.println(Util.isAsc(ary6));

//        System.out.println(Arrays.toString(ary));
//        System.out.println(Arrays.toString(ary2));
//        System.out.println(Arrays.toString(ary3));
//        System.out.println(Arrays.toString(ary4));
//        System.out.println(Arrays.toString(ary5));
//        System.out.println(Arrays.toString(ary6));
    }

    //最差的冒泡
    public void sort1(int[] ary) {
        long startTime = System.nanoTime();

        for (int i = 0, len = ary.length; i < len; i++) {
            for (int j = 1; j < len; j++) {
                if (ary[j] < ary[j - 1]) {
                    Util.swap(ary, j, j - 1);
//                    int temp = ary[j];
//                    ary[j] = ary[j-1];
//                    ary[j-1] = temp;
                }
            }
        }

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

    //内层循环次数，递减一次; 因为每次内层循环都将较大的值向后交换了
    public void sort2(int[] ary) {
        long startTime = System.nanoTime();

        for (int i = 0, len = ary.length; i < len; i++) {
            for (int j = 1; j < len - i; j++) {
                if (ary[j] < ary[j - 1]) {
                    Util.swap(ary, j, j - 1);
                }
            }
        }

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

    //设 bool-flag， 内层循环有交换设 true， 外层循环条件 while(flag)，同样内层循环次数递减
    //当第 n 次内层循环有序，就退出外层循环
    public void sort3(int[] ary) {
        long startTime = System.nanoTime();

        boolean flag = true;
        int len = ary.length;
        while (flag) {
            flag = false;
            for (int i = 1; i < len; i++) {
                if (ary[i] < ary[i - 1]) {
                    Util.swap(ary, i, i - 1);
                    flag = true;
                }
            }
            len--;
        }

        /*for (int i = 0; i < len; i++) {
            if (flag) {
                flag = false;
                for (int j = 1; j < len - i; j++) {
                    if (ary[j] < ary[j - 1]) {
                        Util.swap(ary, j, j - 1);
                        flag = true;
                    }
                }
            } else {
                break;
            }
        }*/

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

    //设 int-flag 与 len， flag=内层循环最后的交换位置，len=flag即下次内层循环次数小于len
    //因为每次内层循环都将较大的值向后交换了。当第 n 次内层循环有序，就退出外层循环
    public void sort4(int[] ary) {
        long startTime = System.nanoTime();

        int len = ary.length;
        int flag = len;
        while (flag > 0) {
            flag = 0;
            for (int i = 1; i < len; i++) {
                if (ary[i] < ary[i - 1]) {
                    Util.swap(ary, i, i - 1);
                    flag = i;//i 现在是较大的元素，i 之前已有序
                }
            }
            len = flag;
        }

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

    //鸡尾酒排序、双向的冒泡排序
    public void sort5(int[] ary) {
        long startTime = System.nanoTime();

        int l = 0;
        int r = ary.length - 1;
        int tl, tr;
        while (l < r) {
            tl = l + 1;
            tr = r - 1;

            for (int i = l; i < r; i++) {
                if (ary[i + 1] < ary[i]) {
                    Util.swap(ary, i, i + 1);
                    tr = i;
                }
            }
            r = tr;
            for (int i = r; i > l; i--) {
                if (ary[i] < ary[i - 1]) {
                    Util.swap(ary, i, i - 1);
                    tl = i;
                }
            }
            l = tl;


            /*for (int i = r; i > l; i--) {
                if (ary[i] < ary[i - 1]) {
                    Util.swap(ary, i, i - 1);
                    tl = i;
                }
            }
            l = tl;

            for (int i = l; i < r; i++) {
                if (ary[i + 1] < ary[i]) {
                    Util.swap(ary, i, i + 1);
                    tr = i;
                }
            }
            r = tr;*/
        }

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }


    public void sort6(int[] ary) {
        long startTime = System.nanoTime();

        int len = ary.length;
        for (int i = 1; i < len; i++) {
//            for (int j = i; j > 0; j--) {
//                if (ary[j] < ary[j - 1]) {
//                    Util.swap(ary, j, j - 1);
//                } else {
//                    break;
//                }
//            }
            //前 j 个元素间，满足条件，才交换
            for (int j = i; j > 0 && ary[j] < ary[j - 1]; j--) {
                Util.swap(ary, j, j - 1);
            }
        }

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }
}
