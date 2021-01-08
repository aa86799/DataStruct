package com.stone.stuct.heap;

import java.util.Random;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/11 19 18
 */
public class PriorityQueue<E extends Comparable<E>> implements IQueue<E> {

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        long startTime = System.nanoTime();

        int count = 1000*1000;
        Random random = new Random();
        //添加
        for (int i = 1; i < count; i++) {
            queue.enqueue(random.nextInt(i));
        }

        //删除
        for (int i = 0; i < count / 3; i++) {
            queue.dequeue();
        }

        double time = (System.nanoTime() - startTime) / 1.0e9;
        System.out.println("time = " + time);
    }

    private MaxHeap<E> heap;

    public PriorityQueue() {
        heap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return heap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        heap.add(e);
    }

    @Override
    public E dequeue() {
        return heap.removeMax();
    }

    @Override
    public E getFront() {
        return heap.findMax();
    }
}
