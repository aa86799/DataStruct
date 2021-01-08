package com.stone.algorithm.sort;

import java.util.Arrays;

/**
 * desc  : 归并排序. 分治思想.  O(n * log n)
 *          可以 自顶向下的 归并排序 --> sort1() sort2()   先向下拆分，再向上归并
 *          也可 自底向上的 归并排序 --> sort3()
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/24 17 29
 */
public class D_MergeSort {
    public static void main(String[] args) {
        int[] ary = Util.buildRandomArray(1000 * 100 / 4 );
//        int[] ary = Util.buildRandomArray(20);
//        int[] ary = Util.buildDescArray(1000 * 100 / 4);
        int[] ary2 = ary.clone();
        int[] ary3 = ary.clone();
        System.out.println(Arrays.toString(ary));

        D_MergeSort mergeSort = new D_MergeSort();
        mergeSort.sort1(ary);
        mergeSort.sort2(ary2);
        mergeSort.sort3(ary3);
        System.out.println(Util.isAsc(ary));
        System.out.println(Util.isAsc(ary2));
        System.out.println(Util.isAsc(ary3));

        System.out.println(Arrays.toString(ary));
        System.out.println(Arrays.toString(ary2));
        System.out.println(Arrays.toString(ary3));
    }

    /*
     * 不断的二分，或者说递归的二分，直至处理仅一个元素，一个元素时就认为是有序的，
     * 然后向上归并，
     *      左右两个子序列都是有序的，
     *      声明三个值，分别表示 结果集的 开始索引、左序列的开始索引、右序列的开始索引： kij
     */

    public void sort1(int[] ary) {
        long startTime = System.nanoTime();

        mergeSort1(ary, 0, ary.length - 1);

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

    private void mergeSort1(int[] ary, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort1(ary, start, mid);
        mergeSort1(ary, mid + 1, end);

        //直接归并
//        merge(ary, start, mid, end);

        //先比较左右子序，左边的最大值 比 右边的最小值
        if (ary[mid] > ary[mid + 1]) {//[start,end]是否是降序的，是就调用 merge 合并成升序
            merge(ary, start, mid, end);
        }
    }

    public void sort2(int[] ary) {
        long startTime = System.nanoTime();

        mergeSort2(ary, 0, ary.length - 1);

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

    //在数量级较小时，使用插入排序，效果更高。 是对 sort1的优化
    private void mergeSort2(int[] ary, int start, int end) {
        if (start >= end) {
            return;
        }

        //在数量级较小时使用插入排序更快,  我的环境上，100以内表现较好
        if (end - start <= 25) {
            Util.insertSort(ary, start, end);
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort2(ary, start, mid);
        mergeSort2(ary, mid + 1, end);

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
            } else {//左边大于右边
                ary[k] = res[j - start];
                j++;
            }

            k++;
        }
    }

    //自底向上
    public void sort3(int[] ary) {
        long startTime = System.nanoTime();

        int len = ary.length;
        /*
         * 自底向上的归并排序算法的思想就是数组中先一个一个归并成两两有序的序列，两两有序的序列归并成四个四个有序的序列，
         * 然后四个四个有序的序列归并八个八个有序的序列，以此类推，直到，归并的长度大于整个数组的长度，此时整个数组有序。
         * 需要注意的是数组按照归并长度划分，最后一个子数组可能不满足长度要求(即不为2^x)，这个情况需要特殊处理
         */
        /*
         * i 表示 每次归并的 size，  外层：第一次循环是1，第二次循环是2，然后是4,8,16...
         * 内层循环 j 的步长是外层循环的2倍
         *    i=1,  j=[0,2,4,6,8,10..]    两两归并
         *    i=2,  j=[0,4,8,12,16,20..]  四四归并
         *    i=4,  j=[0,8,16,24,32..]    八八归并
         *    i=8,  j=[0,16,32,48,64..]   十六 十六 归并
         *
         *  i=1,  j=[0,2,4,6,8,10..]    两两归并
         *      j=0,  要将 [0,1]的元素进行归并
         *      j=2,  要将 [2,3]的元素进行归并
         *      j=4,  要将 [4,5]的元素进行归并
         *      j=6,  要将 [6,7]的元素进行归并
         *      归并参数： start=j; end=j+1 || j+ 2i-1 <==> j+i+i-1;
         *              mid =
         *  i=8,  j=[0,16,32,48,64..]
         *      j=0, [0,15]
         *      j=16 [16,31]
         *      j=32 [32,47]
         *      j=48 [48,63]
         *      归并参数： start=j; end=j+1就不对了，  j+2i-1 <==> j+i+i-1; 是对的
         *              mid = (j + (j+i+i-1)) /2 = (2j+2i-1)/2 => j+i-1/2，由于索引值要整数=> j+i-1
         *         由于mid < len， 所以 j+i-1 <len. 实际测试 j+i<len 也是对的
         *         由于 end<=len-1, 而 end=j+i+i-1是可能 > len-1的, 所以 end 值要
         *              Math.min(len-1, j+i+i-1)
         */
//        for (int i = 1; i < len; i = i + i) {
        for (int i = 1; i < len; i <<= 1) {
            for (int j = 0; j + i /*-1 */< len; j += i + i) {
                merge(ary, j, j + i - 1, Math.min(len - 1, j + i + i - 1));
            }
        }

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

}
