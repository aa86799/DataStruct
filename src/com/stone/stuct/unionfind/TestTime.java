package com.stone.stuct.unionfind;

import java.util.Random;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/25 11 24
 */
public class TestTime {

    public static void main(String[] args) {
        int count  = 1000 * 10000;

//        UnionFind1 uf1 = new UnionFind1(count);
//        System.out.println(testRunTime(uf1, count));
//
//        UnionFind2 uf2 = new UnionFind2(count);
//        System.out.println(testRunTime(uf2, count));
//        /*
//         * 在测试用例不断扩大后，uf2的性能不一定强于 uf1。
//         * 在 uf2中， isConnected()的效率为 o(h) 比 uf1是低的
//         *      且在 parent 指向时，没有考虑 p、q 子树哪个大的问题，会造成最终合并后的树深度过大
//         *         该问题在 uf3中解决
//         */
//
//        //size 优化
        UnionFind3 uf3 = new UnionFind3(count);
        System.out.println(testRunTime(uf3, count));
//
//        /* 因使用随机样本，是否连接 的结果也不一定 */
//        /* 实际的一个应用，src[]表示真实的集合，判断元素是否相连 */
//        int[] src = new int[count];
//        for (int i = 0; i < count; i++) {
//            src[i] = i * 10;
//        }
//        System.out.println(String.format("并查集 uf%d中：%s 与 %s 是 %s的", 1, src[10], src[80],
//                uf1.isConnected(10, 80) ? "相连" : "不相连"));
//        System.out.println(String.format("并查集 uf%d中：%s 与 %s 是 %s的", 2, src[10], src[80],
//                uf2.isConnected(10, 80) ? "相连" : "不相连"));
//        System.out.println(String.format("并查集 uf%d中：%s 与 %s 是 %s的", 3, src[10], src[80],
//                uf3.isConnected(10, 80) ? "相连" : "不相连"));
        /* 实际应用中， src 可以是一个其它类型的集合 */


        /*
         * rank 优化，在当前运行环境下，10万量级以上，才略优于 uf3-size 优化。
         */
        UnionFind4 uf4 = new UnionFind4(count);
        System.out.println(testRunTime(uf4, count));

        /*
         * rank 优化，加 路径压缩
         * 当前环境下，千万量级，优势较uf3、4 明显
         */
        UnionFind5 uf5 = new UnionFind5(count);
        System.out.println(testRunTime(uf5, count));

        System.out.println(uf4.isConnected(10,80));
        System.out.println(uf5.isConnected(10,80));

    }

    private static double testRunTime(UF uf, int count) {
        long startTime = System.nanoTime();

        int size = uf.getSize();

        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }

        for (int i = 0; i < count; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        return (System.nanoTime() - startTime) / 1.0e9;
    }

}
