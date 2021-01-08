package com.stone.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * desc  : 求(随机的)数组中第 n 大的元素
 *          求(随机的)数组中前 n 大的元素, 或者说 最大的 n 个元素
 *
 *          本例用堆排序的思想解决 (非完全堆排)
 *
 *          同理也可以实现，求第 n 小的，前n 小的，最小的 n 个元素
 *
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/26 00 18
 */
public class al_TheRdMaxValue2 {
    int destIndex;

    public static void main(String[] args) {
        int[] ary = Util.buildRandomArraySet(1000 * 1000);
//        int[] ary = Util.buildRandomArray(20);
//        System.out.println(Arrays.toString(ary));
        al_TheRdMaxValue2 maxValue = new al_TheRdMaxValue2();
        int n = 1000; //第n大
        maxValue.destIndex = n - 1; //0,1,2,3,4
        maxValue.heapSort(ary);
//        System.out.println(Arrays.toString(ary));
        System.out.println(ary[ary.length - 1 - maxValue.destIndex]);

        System.out.println(String.format("最大的%d 个元素", n));
        for (int i = ary.length - 1; i >= ary.length - 1 -maxValue.destIndex; i--) {
            System.out.print(ary[i] + ", ");
        }
        System.out.println();

        System.out.println(String.format("第%d大的元素%d", n, ary[ary.length - n]));
    }

    /*
     * 要求最大的，即最后要求是倒序的，这时要先创建的是小顶堆，然后交换首尾再下移
     *    每次下移时，末端是较小的元素，这样末端就是有序的
     *   所以，可以反过来，先建大顶堆，让末端是较大的元素，当 所要求的位置或数量满足后，就退出
     */

    public void heapSort(int[] ary) {
        long startTime = System.nanoTime();

        int len = ary.length;
        for (int i = (len - 1) / 2; i >= 0; i--) {
            siftDown(ary, i, len - 1);
        }
        //限定 i 的区间，这样只循环  destIndex+1次
        for (int i = len - 1; i >= len -1 - destIndex; i--) {
            Util.swap(ary, 0, i);
            siftDown(ary, 0, i - 1);
        }


        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

    private void siftDown(int[] ary, int start, int end) {
        //子节点指标
        int leftChildIndex = start * 2 + 1;
        while (leftChildIndex <= end) { //若子节点指标在范围内才做比较
            if (leftChildIndex + 1 <= end && ary[leftChildIndex] < ary[leftChildIndex + 1]) { //先比较两个子节点大小
                leftChildIndex++;
            }
            if (ary[start] >= ary[leftChildIndex]) {//如果父节点小于子节点代表调整完毕，直接跳出函数
                return;
            } else { //否则交换父子内容再继续子节点和孙节点比较
                Util.swap(ary, start, leftChildIndex);
                start = leftChildIndex;
                leftChildIndex = start * 2 + 1;
            }
        }

    }
}
