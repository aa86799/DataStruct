package algorithm.sort;

import java.util.Arrays;

/**
 * desc  : 划分排序
 *              指定一个元素，将小于它的排一边，大于它的排另一边
 *              不保证，整体有序，也不保证，指定元素的左右两边有序
 *            划分时的 交换：O(n)
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/24 22 20
 */
public class E_PartitionSort {

    public static void main(String[] args) {
        int[] ary = Util.buildRandomArray(20000 );
        System.out.println(Arrays.toString(ary));

        E_PartitionSort partitionSort = new E_PartitionSort();
        partitionSort.sort(ary, 10);
        System.out.println(Arrays.toString(ary));
    }


    public void sort(int[] ary, int e) {
        long startTime = System.nanoTime();

        int len = ary.length;
        int left = 0;
        int right = len - 1;

        int lp = left, rp = right; //左右指针

        while (true) {
            // 分成左右两边同时进行比较，一边从左向右，一边从右向左

            while (lp < right && ary[lp++] < e); //不满足时 ary[lp-1] >=e
            while (rp >= left && ary[rp--] >= e); //不满足时 ary[rp+1] <e

            //lp、rp 位置的值进行交换
            if (lp - 1 < rp + 1) {
                Util.swap(ary, lp - 1, rp + 1);
            } else {
                //左指针比右指针大了，说明 左指针指向的值也比右指针指向的值大，即满足划分条件了
                break;
            }
        }
        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

}
