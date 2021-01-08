package com.stone.stuct.set;

import java.util.Random;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/25 11 24
 */
public class TestTime {

    public static void main(String[] args) {
        LinkedListSet<Integer> set = new LinkedListSet<>();
        System.out.println("time1:" + testRunTime(set));

        BSTSet<Integer> set2 = new BSTSet<>(); //高效
        System.out.println("time2:" + testRunTime(set2));

        BSTMapSet<Integer> set3 = new BSTMapSet<>(); //高效
        System.out.println("time3:" + testRunTime(set3));
    }

    private static double testRunTime(ISet<Integer> set) {
        long startTime = System.nanoTime();

        int count = 1000*100;
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            set.add(random.nextInt(1000));
        }

        //有序的添加，bstset 效率低；若将 bst 的添加实现改为每次添加一个新的都做为 root，就和这里的链表实现的效率一样了
//        for (int i = 0; i < count; i++) {
//            set.add(i);
//        }

        return (System.nanoTime() - startTime) / 1.0e9;
    }
}
