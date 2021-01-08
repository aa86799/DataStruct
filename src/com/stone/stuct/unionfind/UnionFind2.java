package com.stone.stuct.unionfind;


/**
 * desc  : 并查集
 *              树中根节点思想。每个编号均对应上一级编号
 *              查找时，向上找到根。
 *              合并时，用 p 的根指向 q 的根 (或反之)
 *
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/14 14 14
 */
public class UnionFind2 implements UF {

    private int[] parent;

    public UnionFind2(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    //查找元素 p 对应的集合编号。  p 是元素索引
    // O(h)， h 为树的高度，肯定比整个元素的数量小
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound");
        }
        //查询根 编号
        while (p != parent[p]) {
            p = parent[p];
        }
        /*
         *   0 1 2 3 4 5 6 7 8 9
         *   0 1 1 8 3 0 5 1 8 8
         *   find(4) = 8
         */
        return p; //return parent[p] 是一样的
    }


    @Override //O(h)
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
//        parent[pRoot] = parent[qRoot];
        parent[pRoot] = qRoot; //这时 pRoot=parent[pRoot]  qRoot=parent[qRoot]

    }

    @Override  //O(h)
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int getSize() {
        return parent.length;
    }


}
