package com.stone.stuct.segmenttree;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/12 20 22
 */
public interface Merger<E> {

    E merge(E a, E b);
}
