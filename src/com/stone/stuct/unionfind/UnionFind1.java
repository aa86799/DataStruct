package com.stone.stuct.unionfind;

/**
 * desc  :  并查集
 *              合并元素编号时，仅使编号相等  即赋值另一个元素的编号
 *              合并时 是 O(n)
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/14 14 14
 */
public class UnionFind1 implements UF {

    private int[] id;

    public UnionFind1(int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    //查找元素 p 对应的集合编号。  p 是元素索引
    // O(1)
    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p is out of bound");
        }

        return id[p];
    }


    @Override //O(n)
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }

            //or
//            if (id[i] == qID) {
//                id[i] = pID;
//            }
        }
    }

    @Override  //O(1)
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int getSize() {
        return id.length;
    }
}
