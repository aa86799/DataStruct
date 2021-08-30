package algorithm.sort;

import java.util.Arrays;

/**
 * desc  : root 为最大(本例是最大的)的 二叉堆。
 *          基于数组实现.
 *
 *          堆仅保证首个实际元素是最大的/最小的，不保证所有非叶子节点都比左右子大/小
 *
 *          堆的 root 是最大或最小值，
 *              每个非叶子节点，都比左右子节点大；实际存储是以数组实现的，方便用下标
 *              找到正确的父级、左子、右子索引位置；且用数组，可以看成是一个完全的二叉树；
 *
 *         add  siftUp(int k): 添加到内部数组尾部，从尾部循环上移. O(n * log n)
 *         remove siftDown(int k); 将首尾互换，删除尾部(这时最大值)，从首部循环下移. O(n * log n)
 *         siftDown: 堆化已有的数组，降序从最后一个非叶子节点开始遍历下移. O(n)
 *         replaceMax: 找到最大值用于返回，替换首部值，从首位下移，保持堆的性质
 *
 *          本例实际元素索引从1开始
 *
 *          本例实现堆排序，直接在数组中进行堆排序，  也叫原地堆排序
 *
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/11 14 39
 */
public class G_MaxHeapSort2 {

    public static void main(String[] args) {

        int[] ary = Util.buildRandomArray(200);
//        int[] ary = {3, 7, 8, 11, 9, 6, 18};
        int[] ary2 = ary.clone();
        System.out.println(Arrays.toString(ary));

        G_MaxHeapSort2 heap = new G_MaxHeapSort2();
        heap.heapSort(ary);

        System.out.println(Arrays.toString(ary));

        for (int i = 1; i < ary.length; i++) {
            if (ary[0] < ary[i]) {//非降序
                throw new IllegalArgumentException("error");
            }
        }
        System.out.println("completed.");


        System.out.println("----------");
        System.out.println(Arrays.toString(ary2));
        heap.heapSort2(ary2);
        System.out.println(Arrays.toString(ary2));
        for (int i = 1; i < ary2.length; i++) {
            if (ary2[0] < ary2[i]) {//非降序
//                throw new IllegalArgumentException("error");
            }
        }
        System.out.println("completed.");

    }

    /*
     * 原地堆排序，看成是一个堆化的过程
     * 即要有  siftDown  最后一个非叶子开始  ，即末尾的父索引
     *
     *        3             3            3              3          18         18
     *     7    8     =>  7   18  =>  11   18   =>  11     18  => 11 3   => 11 3
     *   11 9  6  18    11 9  6 8    7 9  6  8     9  7   6 8    97  68     97 68
     *
     *  当前堆仅保证首个实际元素是最大的/最小的，不保证所有非叶子节点都比左右子大/小
     *
     *  O(n * log n)
     */
    public void heapSort(int[] ary) {
        long startTime = System.nanoTime();

        int len = ary.length;

        //i 初始值为 最后一位的 父索引
        for (int i = (len - 1 - 1) / 2; i >= 0; i--) {
            //siftDown
            int leftChildIndex = 2 * i + 1;
            int rightChildIndex = 2 * i + 2;
            while (leftChildIndex < len) {
                //若有右子，比较左右，将大的给到左； 若没有右子，那左子就默认是大的值
                if (rightChildIndex < len && ary[leftChildIndex] < ary[rightChildIndex]) {
                    leftChildIndex = rightChildIndex;
                    std1++;
                }
                //当前值 大于 左右中最大值，则退出
                if (ary[i] >= ary[leftChildIndex]) {
                    std1++;
                    break;
                } else {
                    Util.swap(ary, i, leftChildIndex);
                    i = leftChildIndex;
                    std1++;
                }
            }
        }

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
        System.out.println("siftDow 执行次数：" + std1);
    }

    /*
     * 优化堆排序
     *
     * 目标是最大堆
     *      先通过堆化(最后一非叶节点)下移，得到小顶堆，仅是顶部最小
     *      然后再循环 i = len - 1; i > 0; i--
     *          交换首尾， 将最小值放在最末端   swap(0,i)
     *          再从头堆化，siftDown(0,i-1); i位置不参与堆化，取出次小值在顶端
     *          再循环下一次，次小值交换到次末端，再堆化下移
     *        如此类推，最终形成从大到小的序列
     *        因为保证了末端有序，且下移仅发生在从首到末端有序之前，减少了下移的次数，
     *          所以效率 比 sort1快
     *
     *  sort1的缺点
     *      不保证完全有序，中间节点就可能发生重复的下移操作
     *          i=leftChildIndex 由这句引发
     *
     */
    public void heapSort2(int[] ary) {
        long startTime = System.nanoTime();

        int len = ary.length;
        for (int i = (len - 1) / 2; i >= 0; i--) {
            siftDown(ary, i, len - 1);
        }
        for (int i = len - 1; i > 0; i--) {
            Util.swap(ary, 0, i);
            siftDown(ary, 0, i - 1);
        }

        System.out.println("total time:" + ((System.nanoTime() - startTime) / 1.0e9));
        System.out.println("siftDow 执行次数：" + std2);
    }

    private  int std1;
    private  int std2;
    private void siftDown(int[] ary, int start, int end) {
        //子节点指标
        int leftChildIndex = start * 2 + 1;
        while (leftChildIndex <= end) { //若子节点指标在范围内才做比较
            if (leftChildIndex + 1 <= end && ary[leftChildIndex] > ary[leftChildIndex + 1]) { //先比较两个子节点大小，选择最小的
                std2++;
                leftChildIndex++;
            }
            if (ary[start] < ary[leftChildIndex]) {//如果父节点小于子节点代表调整完毕，直接跳出函数
                std2++;
                return;
            } else { //否则交换父子内容再继续子节点和孙节点比较
                std2++;
                Util.swap(ary, start, leftChildIndex);
                start = leftChildIndex;
                leftChildIndex = start * 2 + 1;
            }
        }

    }
}
