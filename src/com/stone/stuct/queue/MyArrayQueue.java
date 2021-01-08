package com.stone.stuct.queue;

/**
 * desc  : 基于底层数组(MyDynamicGenericArray)实现; 队列 先进先出 (一边进，另一边出);  FIFO
 *          出队O(n)
 *          入队O(1)
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/23 23 54
 */
public class MyArrayQueue<E> implements IQueue<E> {

    public static void main(String[] args) {
        MyArrayQueue<String> queue = new MyArrayQueue<>(5);
        for (int i = 0; i < 4; i++) {
            queue.enqueue(i+"-item");
        }
        System.out.println(queue);

        queue.enqueue("111");
        queue.enqueue("222");
        System.out.println(queue);

        queue.dequeue();
        System.out.println(queue);

        System.out.println(queue.getFront());
    }

    private MyDynamicGenericArray<E> array;

    public MyArrayQueue(int capacity) {
        this.array = new MyDynamicGenericArray<E>(capacity);
    }

    public MyArrayQueue() {
        this.array = new MyDynamicGenericArray<E>();
    }


    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("queue size: %d,  queue: \n", getSize()));
        sb.append("front[");
        for (int i = 0; i < getSize(); i++) {
            sb.append(array.get(i));
            if (i != getSize() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]tail\n");
        return sb.toString();
    }
}
