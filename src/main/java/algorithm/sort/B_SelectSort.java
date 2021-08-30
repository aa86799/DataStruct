package algorithm.sort;

import java.util.Arrays;

/**
 * desc  : 选择排序 O(n^2)
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/23 19 37
 */
public class B_SelectSort {
    public static void main(String[] args) {
        int[] ary = Util.buildRandomArray(1000 * 100 / 4);
//        int[] ary = Util.buildRandomArray(30);
//        int[] ary = Util.buildDescArray(1000 * 100 / 4);
        int[] ary2 = ary.clone();

        B_SelectSort selectSort = new B_SelectSort();
        selectSort.sort1(ary);
        selectSort.sort2(ary2);
        System.out.println(Util.isAsc(ary));
        System.out.println(Util.isAsc(ary2));
//        System.out.println(Arrays.toString(ary));
//        System.out.println(Arrays.toString(ary2));
    }

    //从前向后选择；每次内层循环找到最小值索引，交换到前面去，前面有序
    public void sort1(int[] ary) {
        long startTime = System.nanoTime();

        int selectIndex;
        int len = ary.length;
        for (int i = 0; i < len; i++) {
            selectIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (ary[selectIndex] > ary[j]) {
                    selectIndex = j; //每轮内层循环得到最小值的 index
                }
                //如下则是得到大的 index
//                if (ary[j] > ary[selectIndex]) {
//                    selectIndex = j; //每轮内层循环得到最大值的 index
//                }
            }
            if (selectIndex != i) {
                Util.swap(ary, selectIndex, i); //从前向后置换
            }
//            System.out.println(Arrays.toString(ary));
        }

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

    //从后向前选择；每次内层循环找到最大值索引，交换到后面去，后面有序
    public void sort2(int[] ary) {
        long startTime = System.nanoTime();

        int len = ary.length;
        int selectIndex;
        for (int i = len - 1; i >= 0; i--) {
            selectIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (ary[selectIndex] < ary[j]) {
                    selectIndex = j; //每轮内层循环得到最小值的 index
                }
            }
            if (selectIndex != i) {
                Util.swap(ary, selectIndex, i); //从后向前置换
            }
//            System.out.println(Arrays.toString(ary));
        }


        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

}
