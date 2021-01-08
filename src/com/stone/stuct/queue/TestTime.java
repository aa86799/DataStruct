package com.stone.stuct.queue;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/25 11 24
 */
public class TestTime {

    public static void main(String[] args) {
        MyArrayQueue<Integer> queue1 = new MyArrayQueue<>();
        System.out.println("time1:" + testRunTime(queue1));

        MyLoopQueue<Integer> queue2 = new MyLoopQueue<>(); //更高效
        System.out.println("time2:" + testRunTime(queue2));
    }

    private static double testRunTime(IQueue<Integer> queue) {
        long startTime = System.nanoTime();

        int count = 1000*100;
        //添加
        for (int i = 0; i < count; i++) {
            queue.enqueue(i);
        }

        //删除
        for (int i = 0; i < count / 3; i++) {
            queue.dequeue();
        }

        return (System.nanoTime() - startTime) / 1.0e9;
    }
}
