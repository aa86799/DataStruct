package algorithm.sort;

import java.util.Arrays;

/**
 * desc  : 插入排序  O(n^2)
 *          对近乎有序的 序列排序，效率非常高。
 *          所以在其它 o(log n)级的排序算法中，在少数量级时 (如<30)时，可以使用插入排序。
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/24 01 22
 */
public class C_InsertSort {
    public static void main(String[] args) {
        int[] ary = Util.buildRandomArray(1000 * 100 / 4 );
//        int[] ary = Util.buildRandomArray(1000 *100 );
//        int[] ary = Util.buildDescArray(1000 * 100 / 4);
        int[] ary2 = ary.clone();

        C_InsertSort insertSort = new C_InsertSort();
        insertSort.sort1(ary);
        insertSort.sort2(ary2);
        System.out.println(Util.isAsc(ary));
        System.out.println(Util.isAsc(ary2));
//        System.out.println(Arrays.toString(ary));
//        System.out.println(Arrays.toString(ary2));


    }


    //直接插入排序
    public void sort1(int[] ary) {
        long startTime = System.nanoTime();

        //这里用 ary[i]去比较和赋值，是错误的。在内层循环中 ary[i]可能被赋值成 ary[j-1]
        /*int len = ary.length;
        for (int i = 1; i < len; i++) {
            int j;
            for (j = i; j > 0 && ary[i] < ary[j-1]; j--) {
                ary[j] = ary[j - 1];
            }
            ary[j] = ary[i];
        }*/

        int len = ary.length;
        for (int i = 1; i < len; i++) {
            int e = ary[i];
            int j; //保存元素 e 应该插入的位置
            //前 j 个元素间，满足条件，才交换
            for (j = i; j > 0 && e < ary[j - 1]; j--) {
                ary[j] = ary[j - 1];//大的值后移
            }
            ary[j] = e;
        }

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }

    //二分插入排序；数量级较大时会略慢于sort2，因为二分查找的时间过长；数量级较小时略快于 sort2
    public void sort2(int[] ary) {
        long startTime = System.nanoTime();

        int len = ary.length;
        for (int i = 1; i < len; i++) {
            int l = 0;
            int r = i - 1;
            int e = ary[i];
            //二分查找要元素 e 要插入的位置
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (e < ary[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            //与直接插入排序一样，插入位置之后的元素向后移
            //必须是从后向前；若是从前向后：那就会造成该次循环后所影响的索引位置的值都相同
//            for (int j = i - 1; j >= l; j--) {
//                ary[j + 1] = ary[j];
//            }
            //或
//            for (int j = i; j > l; j--) {
//                ary[j] = ary[j-1];
//            }
            // 从 l 处复制 i-l 个，到 l+1处
            if (i - l >= 0) System.arraycopy(ary, l, ary, l + 1, i - l);
            ary[l] = e;

        }

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
    }
}
