package com.stone.stuct.set;

/**
 * desc  : set 集合：元素不可重复
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/2/18 14 19
 */
public interface ISet<E> {

    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty();
}
