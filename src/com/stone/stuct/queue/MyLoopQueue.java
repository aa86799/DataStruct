package com.stone.stuct.queue;


/**
 * desc  : 循环(回环)队列
 *          基于数组实现；标识队头和队尾的索引；
 *
 *          如， 初始 front=tail=size=0
 *          每入队一个元素，size++; tail++;
 *          每出队一个元素，front++;
 *          情况1：当一直出队，front 最终 = tail， 这时队列为空
 *          情况2：当出队一定数量后；这时若当前已达到队尾，再入队，则；
 *                  当持续入队后，也会达成  front = tail 的条件；
 *          情况1和2，它们最终是相互违背的；那么可以
 *              初始时，多一个length；实际容量 data.length - 1;
 *
 *          最终：
 *          入队时，判断 (tail + 1) % data.length ==0 扩容2倍；
 *              data[tail] = e;
 *              tail = (tail + 1) % data.length;
 *              size++;
 *          出队时，
 *              E e = data[front];
 *              data[front] = null;
 *              front =  (front + 1) % data.length;
 *              size--;
 *              if (size == capacity/4 && capacit/2 !=0) 缩容为1/2;
 *              return e;
 *
 *          出队O(1)
 *          入队O(1)
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/24 00 20
 */
public class MyLoopQueue<E> implements IQueue<E> {

    public static void main(String[] args) {
        MyLoopQueue<Integer> queue = new MyLoopQueue<>(5);
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

    private E[] data;
    private int front, tail;
    private int size;

    public MyLoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public MyLoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        //总的是6  tail=5 要扩容；
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("cannot dequeue from empty queue");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("cannot get front from empty queue");
        }

        return data[front];
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];

        /*for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;*/

        //与上面等价的 循环方式
        int j = 0;
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            newData[j++] = data[i];
        }
        data = newData;
        front = 0;
        tail = size;


    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("loop queue size: %d, capacity: %d,  queue: \n", getSize(), getCapacity()));
        sb.append("front="+front+"[");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            sb.append(data[i]);
            if ((i + 1) % data.length != tail) {
                sb.append(", ");
            }
        }
        sb.append("]tail="+tail+"\n");
        return sb.toString();
    }

}
