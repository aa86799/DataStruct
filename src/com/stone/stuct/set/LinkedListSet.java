package com.stone.stuct.set;

import com.stone.stuct.link.MyLinkedList3;

import java.util.Random;

/**
 * desc  : 基于链表实现的 set
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/2/18 14 31
 */
public class LinkedListSet<E> implements ISet<E> {

    public static void main(String[] args) {
        ISet<Integer> set = new LinkedListSet<>();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            set.add(random.nextInt(10));
        }
        System.out.println(set);
    }

    private MyLinkedList3<E> linkedList;
    public LinkedListSet() {
        linkedList = new MyLinkedList3<>();
    }

    @Override
    public void add(E e) {
        if (!linkedList.contains(e)) {
            linkedList.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        if (linkedList.contains(e)) {
            linkedList.removeFirst();
        }
    }

    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }
}
