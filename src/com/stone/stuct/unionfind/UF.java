package com.stone.stuct.unionfind;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/13 22 04
 */
public interface UF {

    void unionElements(int p, int q);
    boolean isConnected(int p, int q);

    int getSize();
}
