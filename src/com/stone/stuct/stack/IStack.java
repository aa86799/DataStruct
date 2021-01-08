package com.stone.stuct.stack;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/23 11 48
 */
public interface IStack<E> {

    int getSize();
//    int getCapacity();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
