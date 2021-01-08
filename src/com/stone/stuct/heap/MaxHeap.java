package com.stone.stuct.heap;

import java.util.Random;

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
 *          add  siftUp(int k): 添加到内部数组尾部，从尾部循环上移. O(n * log n)
 *          remove siftDown(int k); 将首尾互换，删除尾部(这时最大值)，从首部循环下移. O(n * log n)
 *          heapify: 堆化已有的数组，降序从最后一个非叶子节点开始遍历下移. O(n)
 *          replaceMax: 找到最大值用于返回，替换首部值，从首位下移，保持堆的性质
 *
 *         本例实际元素索引从0开始
 *
 *
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/11 14 39
 */
public class MaxHeap<E extends Comparable<E>> {

    public static void main(String[] args) {
        MaxHeap<Integer> heap = new MaxHeap<>();
        Random random = new Random();
        int n = 15;
        for (int i = 0; i < n; i++) {
            heap.add(random.nextInt(n));
        }

        int[] ary = new int[n];
        for (int i = 0; i < n; i++) {
            ary[i] = heap.removeMax();//每次取最大值，ary 内降序
        }

        for (int i = 1; i < ary.length; i++) {
            if (ary[i - 1] < ary[i]) {//非降序
                throw new IllegalArgumentException("error");
            }
        }
        System.out.println("completed.");


        /*
         * 测试将一个随机数组，直接堆化
         * 遍历每次取出最大，判断是否符合 从大到小的 最大堆的 性质
         */
        Integer[] heapifyAry = new Integer[n];
        for (int i = 0; i < n; i++) {
            heapifyAry[i] = random.nextInt(n);
        }
        MaxHeap<Integer> maxHeapSort = new MaxHeap<>(heapifyAry);
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = maxHeapSort.removeMax();//每次取最大值，ary 内降序
        }

        for (int i = 1; i < res.length; i++) {
            if (res[i - 1] < res[i]) {//非降序
                throw new IllegalArgumentException("error");
            }
        }
        System.out.println("completed2.");
    }

    private MyDynamicGenericArray<E> array;

    public MaxHeap() {
        this(10);
    }

    public MaxHeap(int capacity) {
        this.array = new MyDynamicGenericArray<>(capacity);
    }

    public MaxHeap(E[] ary) {
        this.array = new MyDynamicGenericArray<>(ary);

        /*
         * heapify 堆化一个 数组
         *  从倒数第一个非叶子节点，即最后一个节点的父节点 开始，
         *  直到节点索引0
         *
         *  堆化的过程是 O(n)
         */

        for (int i = parent(ary.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int getSize() {
        return array.getSize();
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

    //返回在完全二叉树中，index位置的元素 的父节点的索引
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 is root");
        }
        return (index - 1) / 2;
        /*
         *     0
         *   1  2
         *  3 4  56
         * 78 910
         */
    }

    //返回在完全二叉树中，index位置的元素 的左子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    //返回在完全二叉树中，index位置的元素 的右子节点的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    //O(n*logn)
    public void add(E e) {
        array.addLast(e);
        if (getSize() > 1) {
            siftUp(getSize() - 1);
        }
    }

    //与父级比， 上移
    private void siftUp(int k) {
        int parent = parent(k);
        E pe = array.get(parent);
        E e = array.get(k);

        while (k > 0 && pe.compareTo(e) < 0) {
            array.swap(k, parent);

            if (parent == 0)
                break;
            k = parent;
            parent = parent(k);
//            e = array.get(k); //这句可省略， e 就是要从尾部上移的值
            pe = array.get(parent);
        }
    }

    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("max not find");
        }
        return array.getFirst();
    }

    //O(n*logn)
    public E removeMax() {
        E ret = findMax();

        if (getSize() > 2) {
            array.swap(0, array.getSize() - 1);
            array.removeLast();
            siftDown(0);
            /*
             * 优先，要移除最大，首位
             * 先将首位与最后一位交换，再移除最后一位；
             * 从首位开始下移
             */
        } else {
            array.removeLast();
        }
        return ret;
    }

    //与孩子比，下移
    private void siftDown(int k) {
        int left;
        int right;
        E lv;
        //完全二叉树中，至少有左子
        while (leftChild(k) < getSize()) {
            left = leftChild(k);
            right = rightChild(k);
            //若有右子，且右子大于左子，  取最大的 所以  left=right
            if (right < getSize() && array.get(right).compareTo(array.get(left)) > 0) {
                left = right;
                //此时 left 就是左右子中 最大值的索引
            }
            if (array.get(k).compareTo(array.get(left)) >= 0) {//比子大了，不需要下移了
                break;
            } else {
                array.swap(k, left);
                k = left;
            }
        }

    }

    //O(n*logn)， 取出最大，替换成 e
    public E replaceMax(E e) {
        E ret = findMax();
        array.set(0, e);
        siftDown(0);
        return ret;
    }

    @Override
    public String toString() {
        return array.toString();
    }
}
