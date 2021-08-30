package stuct.graph;

import java.util.ArrayList;
import java.util.Random;

/**
 * desc  : 稀疏图
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/29 16 41
 */
public class SparseGraph {

    private int n, m; //n个顶点， m 个边
    private boolean isDirected; //是否定向
    private ArrayList[] g;
    public SparseGraph(int n, boolean isDirected) {
        this.n = n;
        this.isDirected = isDirected;
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

    }

    public int V() {
        return n;
    }

    public int E() {
        return m;
    }

    //在顶点 v、w 间建立一条边
    public void addEdge(int v, int w) {
        if (v < 0 || v >= n
                || w < 0 || w >= n) {
            throw new IllegalArgumentException("error");
        }

        if (v != w) {//非自环边

//        if (hasEdge(v, w))  //注释掉， 允许平行(多条)边
//            return;

            g[v].add(w);
            if (!isDirected) {//且是无向
                g[w].add(v);
            }
            m++; //即使是无向的  两顶点间也只有一条边
        }

    }

    private boolean hasEdge(int v, int w) {
        if (v < 0 || v >= n
                || w < 0 || w >= n) {
            throw new IllegalArgumentException("error");
        }

        for (int i = 0; i < g[v].size(); i++) {
            Object o = g[v].get(i);
            if (o != null && (Integer) o == w) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(i).append(":");
            for (int j = 0; j < g[i].size(); j++) {
                builder.append(g[i].get(j)).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
        /*
         * 行内结果是无序的， 即一个顶点，它的相邻顶点是无序的
         */
    }

    public static void main(String[] args) {
        /*
         * N 条边，共 M 条边
         * 随机a、b 顶点，连接 M 条边
         */
        int N = 20;
        int M = 100;
        Random random = new Random();
        SparseGraph sparseGraph = new SparseGraph(N, false);
        for (int i = 0; i < M; i++) {
            int a = (N + random.nextInt(N)) % N;
            int b = (N + random.nextInt(N)) % N;
            sparseGraph.addEdge(a, b);
        }
        System.out.println(sparseGraph);

    }
}
