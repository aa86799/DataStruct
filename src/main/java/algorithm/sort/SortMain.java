package algorithm.sort;

/**
 * desc  : 排序算法 总体测试
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/23 20 53
 */
public class SortMain {
    public static void main(String[] args) {
        int[] ary = Util.buildRandomArray(1000 * 100);
//        int[] ary = Util.buildDescArray(1000 * 1000);
//        System.out.println(Arrays.toString(ary));
        int[] ary2 = ary.clone();
        int[] ary3 = ary.clone();
        int[] ary4 = ary.clone();
        int[] ary5 = ary.clone();
        int[] ary6 = ary.clone();
        int[] ary7 = ary.clone();
        int[] ary8 = ary.clone();
        int[] ary9 = ary.clone();
        int[] ary10 = ary.clone();
        int[] ary11 = ary.clone();
        int[] ary12 = ary.clone();
        int[] ary13 = ary.clone();
        int[] ary14 = ary.clone();
        int[] ary15 = ary.clone();
        int[] ary16 = ary.clone();
        int[] ary17 = ary.clone();
        int[] ary18 = ary.clone();

        System.out.println("A_BubbleSort");
//        A_BubbleSort bubbleSort = new A_BubbleSort();
//        bubbleSort.sort1(ary);
//        bubbleSort.sort2(ary2);
//        bubbleSort.sort3(ary3);
//        bubbleSort.sort4(ary4);
//        bubbleSort.sort5(ary5);
//        System.out.println(Util.isAsc(ary));
//        System.out.println(Util.isAsc(ary2));
//        System.out.println(Util.isAsc(ary3));
//        System.out.println(Util.isAsc(ary4));
//        System.out.println(Util.isAsc(ary5));
        System.out.println("----------------------");

        System.out.println("B_SelectSort");//比冒泡强一些
//        B_SelectSort selectSort = new B_SelectSort();
//        selectSort.sort1(ary6);
//        selectSort.sort2(ary7);
//        System.out.println(Util.isAsc(ary6));
//        System.out.println(Util.isAsc(ary7));
        System.out.println("----------------------");

        System.out.println("C_InsertSort");//比选择强
//        C_InsertSort insertSort = new C_InsertSort();
//        insertSort.sort1(ary8);
//        insertSort.sort2(ary9);
//        System.out.println(Util.isAsc(ary8));
//        System.out.println(Util.isAsc(ary9));
        System.out.println("----------------------");


        System.out.println("D_MergeSort");
        D_MergeSort mergeSort = new D_MergeSort();
        mergeSort.sort1(ary10);
        mergeSort.sort2(ary11);
        mergeSort.sort3(ary12);
        System.out.println(Util.isAsc(ary10));
        System.out.println(Util.isAsc(ary11));
        System.out.println(Util.isAsc(ary12));
        System.out.println("----------------------");

        System.out.println("F_QuickSort");
        F_QuickSort quickSort = new F_QuickSort();
//        quickSort.sort1(ary13); //对于有(倒）序过长的会栈溢出
        quickSort.sort2(ary14);
        quickSort.sort3(ary15);
        quickSort.sort4(ary16);
//        System.out.println(Util.isAsc(ary13));
        System.out.println(Util.isAsc(ary14));
        System.out.println(Util.isAsc(ary15));
        System.out.println(Util.isAsc(ary16));
        System.out.println("----------------------");

        System.out.println("G_MaxHeapSort2");
        G_MaxHeapSort2 maxHeapSort = new G_MaxHeapSort2();
//        maxHeapSort.heapSort(ary17);
        maxHeapSort.heapSort2(ary18);
        System.out.println(Util.isAsc(ary17));
        System.out.println(Util.isAsc(ary18));
        System.out.println("----------------------");

    }
}
