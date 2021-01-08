package com.stone.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * desc  : 快速排序     O(n * log n)
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/24 22 46
 */
public class F_QuickSort {

    public static void main(String[] args) {
//        int[] ary = Util.buildPartDesc(1000 * 100 / 2, 50);
        int[] ary = Util.buildRandomArray(1000 * 100 / 2);
//        int[] ary = Util.buildDescArray(1000 * 100 / 4); //sort1会栈溢出
//        int[] ary = Util.buildRandomArray(200);
//        int[] ary = Util.buildDescArray(200);
        int[] ary2 = ary.clone();
        int[] ary3 = ary.clone();
        int[] ary4 = ary.clone();
        System.out.println(Arrays.toString(ary));

        F_QuickSort quickSort = new F_QuickSort();
        quickSort.sort1(ary);//处理有序时，会溢出
        quickSort.sort2(ary2);
        quickSort.sort3(ary3);
        quickSort.sort4(ary4);
        System.out.println(Util.isAsc(ary));
        System.out.println(Util.isAsc(ary2));
        System.out.println(Util.isAsc(ary3));
        System.out.println(Util.isAsc(ary4));
//        System.out.println(Util.isAsc(ary3));
//        System.out.println(Util.isAsc(ary4));
//        System.out.println(Arrays.toString(ary));
//        System.out.println(Arrays.toString(ary2));
    }

    int xxxx;
    public void sort1(int[] ary) {
        long startTime = System.nanoTime();

        if (ary.length <= 1) return;
        qs1(ary, 0, ary.length - 1);

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

    /*
     * 递归快排，划分时，每次以子序列的左边为基准进行判断-划分；如果序列完全有序，效率会蜕变成 O(n^2)
     * 当输入序列完全有序(此例是倒序，因为这里是顺序排序)，且数量级较大(上万个)或更大时，
     *      这时递归的深度就较大(上万级或以上)，会造成栈溢出
     *
     */
    private void qs1(int[] ary, int l, int r) {
        if (l >= r)
            return;
        int p = partition(ary, l, r);
        qs1(ary, l, p - 1);
        qs1(ary, p + 1, r);
        //p 位置值不变
    }

    /*
     * 划分， 以 l代表的元素值 为基准，
     *   返回的索引 左边， 比该值小，右边大于等于该值.
     *     循环中，在符合if 条件时，交换两个位置值：i, ++j
     *     循环后，交换 j 和 l 位置值。
     *   返回的索引，符合排序顺序
     */
    private int partition(int[] ary, int l, int r) {
        int v = ary[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (ary[i] < v) {
                //小的值交换到  从 l+1之后的位置
                //此时形成 v, [l+1,j] < v,  [j+1,r] > v
                Util.swap(ary, i, ++j);
            }
        }
        //交换后形成： [l,j-1] < v, [j]=v, [j+1,r]>v
        Util.swap(ary, l, j);
        return j;
    }

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
        qs2(ary, l, p - 1);
        qs2(ary, p + 1, r);

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
            if (ary[i] < v) {
                //小的值交换到  从 l+1之后的位置
                //此时形成 v, [l+1,j] < v,  [j+1,r] > v
                Util.swap(ary, i, ++j);
            }
        }
        //交换后形成： [l,j-1] < v, [j]=v, [j+1,r]>v
        Util.swap(ary, l, j);
        return j;
    }

    /*
     * 之前的排序算法中，partition()中的if 语句都是 if(ary[i]<v)
     *   这会造成=v 的数据都在右边。=v 的数据较多时，会造成右边越长，整体越不平衡。
     *    即在 =v时多个连续数据时， j 无变化，造成递归深度过深。
     */

    /*
     * 双路快排
     * 可以解决，上面提到的 连续=v 的问题。
     *
     */
    public void sort3(int[] ary) {
        long startTime = System.nanoTime();

        if (ary.length <= 1) return;
        qs3(ary, 0, ary.length - 1);

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

    private void qs3(int[] ary, int l, int r) {
        if (l >= r)
            return;

        if (r - l <= 30) {
            Util.insertSort(ary, l, r);
            return;
        }

        int p = partition3(ary, l, r);
        qs3(ary, l, p - 1);
        qs3(ary, p + 1, r);
    }

    /*
     * i 记录 <v 的，j 记录 >v 的，  初始 i=l+1, j=r;
     * 分别遍历
     *   从前向后遍历：while([i]<v) i++;
     *   从后向前遍历：while([j]>v) j--;
     * 当 [i]>=v ，[j] <=v 时停止；
     * 这时 [i]、[j]的值要交换
     *
     * 其它细节：i j 的边界问题，外层循环的停止，符合条件时，i j 指针 继续向后/向前移
     *
     */
    private int partition3(int[] ary, int l, int r) {
        //[l,r]间的随机索引
        int rand = l + random.nextInt(r - l) + 1;
        //将随机索引代表的元素 交换到 l 位置
        Util.swap(ary, l, rand);

        int v = ary[l];
        int i = l + 1;
        int j = r;
        while (true) {
            while (i <= r && ary[i] < v) i++;
            while (j >= l + 1 && ary[j] > v) j--;
            if (i > j) { //ary[i] > ary[j]，即后面的比前面的大
                break;
            }
            Util.swap(ary, i, j);
            i++;
            j--;
        }
        Util.swap(ary, l, j);
        return j;
    }

    /*
     * 三路快排
     * 可以解决，上面提到的 连续=v 的问题。
     */
    public void sort4(int[] ary) {
        long startTime = System.nanoTime();

        if (ary.length <= 1) return;
        qs4(ary, 0, ary.length - 1);

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

    /*
     * 分成三部分： <v =v >v
     * i 从左向右遍历索引，
     * lt 表示 <v 的右边界
     * gt 表示 >v 的左边界
     */
    private void qs4(int[] ary, int l, int r) {
        if (l >= r)
            return;

        if (r - l <= 31) {//数据量较小使用插入排序
            Util.insertSort(ary, l, r);
            return;
        }

        //[l,r]间的随机索引
        int rand = l + random.nextInt(r - l) + 1;
        //将随机索引代表的元素 交换到 l 位置
        Util.swap(ary, l, rand);

        int v = ary[l];
        int lt = l + 1 ;
        int gt = r;
        int i = l + 1;
        while (i <= gt) {
            if (ary[i] < v) {
                Util.swap(ary, i, lt);
                i++;
                lt++;
            } else if (ary[i] > v) {
                Util.swap(ary, i, gt);
                gt--;
            } else {
                i++;
            }
        }
        //将 l 代表的 v 值，交换到 最后个 正常lt的位置，之前 lt++了，这里要 --lt;
        //因为：--lt 表示的 是 <v； 交换后， l 的位置就是小于 v 的；--lt 就是 =v 的
        Util.swap(ary, --lt, l);

        qs4(ary, l, lt - 1);
        qs4(ary, gt + 1, r);
    }

    /*
     * 还有个优化手段，就是对于 少量数据 比如 100以内，可以直接使用插入排序，提高效率
     *
     * 性能比较：
     *   对完全随机的序列 4种方式效率差不多；
     *   对完全有序的序列， 第一1种，容易栈溢出；
     *   234，效率也差不多， 第3种 略快；
     */
}
