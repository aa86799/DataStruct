package com.stone.algorithm.sort;

import com.stone.stuct.heap.MyDynamicGenericArray;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * desc  :  索引堆
 * 不改变原有数据，将它们进行堆排序
 * 堆结构中，一个 记录索引的数组，一个记录实际元素的数组
 *
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/26 15 44
 */
public class G_MaxIndexHeap<E extends Comparable<E>> {
    public static void main(String[] args) {
        G_MaxIndexHeap<Integer> heap = new G_MaxIndexHeap<>(10);
        Random random = new Random();
        int n = 15;
        for (int i = 0; i < n; i++) {
            heap.insertItem(random.nextInt(n));
        }
//        System.out.println(heap.toString());

        int[] ary = new int[n];
        for (int i = 0; i < n; i++) {
            ary[i] = heap.extractMax();//每次取最大值，ary 内降序
        }

        for (int i = 1; i < ary.length; i++) {
            if (ary[i - 1] < ary[i]) {//非降序
                throw new IllegalArgumentException("error");
            }
        }
        System.out.println("completed.");


        System.out.println("----------");
        G_MaxIndexHeap<Student> heap1 = new G_MaxIndexHeap<>(5);
        n = 20;
        for (int i = 0; i < n; i++) {
            heap1.insertItem(new Student("name" + i, random.nextInt(101)));
        }
        Student[] students = new Student[n];
//        System.out.println(heap1.toString());
        for (int i = 0; i < n; i++) {
            students[i] = heap1.extractMax();
        }
        System.out.println(Arrays.toString(students));
        for (int i = 1; i < students.length; i++) {
            if (students[i - 1].score < students[i].score) {//非降序
                throw new IllegalArgumentException("error2");
            }
        }
        System.out.println("completed2.");
    }

    private MyDynamicGenericArray<Integer> index;//从头到尾是堆中的位置；保存的值指向arr位置  index[i] = j;
    private MyDynamicGenericArray<E> arr;
    private MyDynamicGenericArray<Integer> reverse;//reverse[j] = index 中的 i
    private int count;

    //构造方法
    public G_MaxIndexHeap(int capacity) {
        this.count = 0;//数量初始化为0
        arr = new MyDynamicGenericArray<>(capacity);//索引从0开始
        index = new MyDynamicGenericArray<Integer>(capacity + 1);
        index.add(0, null);
        arr.add(0, null);

        /*
         * index arr 的实际元素索引从1开始
         */
    }

    //判断当前堆是否为空
    public Boolean isEmpty() {
        return count == 0;
    }

    //返回该最大堆的元素个数
    public int size() {
        return count;
    }

    //插入元素到最大堆
    public void insertItem(E item) {

        count++;
        arr.addLast(item);
        index.addLast(count);

        //向上调整
        shiftUp(count);
    }


    //向上调整
    private void shiftUp(int k) {
        //比较的是arr数组
        //注意此时堆中存储的是index值，比较的是对应index值对应的arr[]数组的值
        if (k > 1 && compare(k/2, k) < 0) {
            //交换的是index数组
            int temp = getI(k / 2);
            index.set(k / 2, getI(k));
            index.set(k, temp);

        } else
            return;

        k = k / 2;
        shiftUp(k);

    }


    //从堆里取出堆顶元素
    public E extractMax() {
        if (count < 1) {
            System.out.println("该最大堆为空");
            return null;
        } else {
            //这里取出来的是arr[]数组中的元素
            //这里调整的还是index
            E item = get(getI(1));
            //将末尾元素放到堆顶
            index.set(1, getI(count));
            count--;//堆的元素个数减一

            //向下调整元素
            shiftDown(1);

            return item;
        }

    }

    //向下调整元素
    private void shiftDown(int k) {
        //如果这个结点有左孩子
        while (2 * k <= count) {
            int j = 2 * k;
            if (j + 1 <= count && compare(j+1, j) > 0)
                j += 1;
            if (compare(j, k) > 0) {
                int temp = getI(j);
                index.set(j, getI(k));
                index.set(k,temp);

                k = j;
            } else
                break;
        }

    }

    private E get(int k) {
        return arr.get(k);
    }

    private int getI(int k) {
        return index.get(k);
    }

    private int compare(int i, int j) {
        return arr.get(index.get(i)).compareTo(arr.get(index.get(j)));
    }

    @Override
    public String toString() {
        return arr.toString();
    }

    private static class Student implements Comparable<Student> {
        String name;
        int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Student o) {
            int res = this.score - o.score;
            if (res > 0) {
                return 1;
            } else if (res < 0) {
                return -1;
            } else {
//                return name.compareTo(o.name);
                return 0;
            }
        }

        @Override
        public String toString() {
//            return "Student{" +
//                    "name='" + name + '\'' +
//                    ", score=" + score +
//                    '}';
            return "{"+ score +'}';
        }
    }
}
