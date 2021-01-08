package com.stone.stuct.set;

import com.stone.stuct.bst.BST;

import java.util.Random;

/**
 * desc  : 基于二分搜索树实现的 set
 *          平均的时间复杂度
 *              add、remove、contains  O(log n)
 *          最坏的情况，
 *              如果加入的数据是近似有序的，即只向一边加入数据，那跟链表基于相似了
 *              这时的时间复杂度与链表一样是  O(n)
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/2/18 14 19
 */
public class BSTSet<E extends Comparable<E>> implements ISet<E> {

    public static void main(String[] args) {
        ISet<Integer> set = new BSTSet<>();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            set.add(random.nextInt(10));
        }
        System.out.println(set);

    }

    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    @Override //O(n)
    public void add(E e) {
        bst.add(e);
    }

    @Override //O(n)
    public void remove(E e) {
        bst.remove(e);
    }

    @Override //O(log n)
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public String toString() {
        return bst.toString();
    }
}
