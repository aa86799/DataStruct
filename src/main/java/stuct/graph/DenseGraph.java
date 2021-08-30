package stuct.graph;


import java.util.Random;

/**
 * desc  : 稠密图
 *
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/29 16 18
 */
public class DenseGraph {

    private int n, m; //n个顶点， m 个边
    private boolean isDirected; //是否定向
    private boolean[][] g;
    public DenseGraph(int n, boolean isDirected) {
        this.n = n;
        this.isDirected = isDirected;
        this.g = new boolean[n][n];
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
        if (hasEdge(v, w))
            return;

        g[v][w] = true;
        if (!isDirected) {//无向
            g[w][v] = true;
        }
        m++; //即使是无向的  两顶点间也只有一条边
    }

    private boolean hasEdge(int v, int w) {
        if (v < 0 || v >= n
                || w < 0 || w >= n) {
            throw new IllegalArgumentException("error");
        }

        return g[v][w];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(i).append(":");
            for (int j = 0; j < n; j++) {
                if (g[i][j]) {//若有边
                    builder.append(j).append(" ");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
        /*
         * 行内结果有无序的， 即一个顶点，它的相邻顶点是有序的
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
        DenseGraph sparseGraph = new DenseGraph(N, false);
        for (int i = 0; i < M; i++) {
            int a = (N + random.nextInt(N)) % N;
            int b = (N + random.nextInt(N)) % N;
            sparseGraph.addEdge(a, b);
        }
        System.out.println(sparseGraph);

    }
}
