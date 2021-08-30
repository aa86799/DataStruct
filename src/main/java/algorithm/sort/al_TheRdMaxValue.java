package algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * desc  : 求(随机的)数组中第 n 大的元素
 *          求(随机的)数组中前 n 大的元素, 或者说 最大的 n 个元素
 *
 *          用快速排序的思想可以解决(非完全快排)
 *
 *          同理也可以实现，求第 n 小的，前n 小的，最小的 n 个元素
 *
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/26 00 18
 */
public class al_TheRdMaxValue {
    int destIndex;

    public static void main(String[] args) {
        int[] ary = Util.buildRandomArraySet(1000 * 1000);
//        int[] ary = Util.buildRandomArray(20);
//        System.out.println(Arrays.toString(ary));
        al_TheRdMaxValue maxValue = new al_TheRdMaxValue();
        int n = 1000; //第n大
        maxValue.destIndex = n-1; //0,1,2,3,4
        maxValue.sort2(ary);
//        System.out.println(Arrays.toString(ary));
        System.out.println(ary[maxValue.destIndex]);

        System.out.println(String.format("最大的%d 个元素", n));
        for (int i = 0; i < n; i++) {
            System.out.print(ary[i] + ", ");
        }
        System.out.println();

        System.out.println(String.format("第%d大的元素%d", n, ary[maxValue.destIndex]));
    }

    /*
     * 可以使用 O(n*log n)的算法，排序后，再用 下标取 对应元素
     *
     * 使用快速排序，达到 O(n)级
     *  log n + log n/2 + log n/4 +... +1 = log(2n) => log n
     */

    //随机快速排序. 即使数量级较大，且完全有序，栈溢出的可能也非常小
    public void sort2(int[] ary) {
        long startTime = System.nanoTime();

        if (ary.length <= 1) return;
        qs2(ary, 0, ary.length - 1);

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

    private void qs2(int[] ary, int l, int r) {
        if (l >= r)
            return;
        int p = partition2(ary, l, r);
        if (destIndex == p) {
            //return;
        } else if (destIndex > p) {
            qs2(ary, p + 1, r);
        } else {//destIndex < p
            qs2(ary, l, p - 1);
        }

        /*
         * p 为标的位， 这左边 > ary[p] 右 <= ary[p]
         *
         * destIndex > p， 目标索引在标的位后面，那么就去后面找
         * destIndex < p， 目标索引在标的位前面，那么就去前面找
         */
    }

    private Random random = new Random();

    private int partition2(int[] ary, int l, int r) {
        //[l,r]间的随机索引
        int rand = l + random.nextInt(r - l) + 1;
        //将随机索引代表的元素 交换到 l 位置
        Util.swap(ary, l, rand);

        int v = ary[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (ary[i] > v) {
                Util.swap(ary, i, ++j);
            }
        }
        Util.swap(ary, l, j);
        return j;
    }
}
