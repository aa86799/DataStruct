package algorithm.sort;

/*
 * x轴向右，y轴向上；x、y轴可以有无限
 * (x,y) 表示在x上，石柱的高度y；
 * 向里面注入水，x=0，所在y轴是无限的，且能做为一个长柱子；x虽也可以无限，但不能做为柱子
 *  如，现在有 (1,0) (2,1) (3,2) (4,3) (5,1) (6,2)
 *
 * 通过画图分析，发现整体就是个不断找最大值的问题
 *  从x=0 开始，找到第一个最大y值，设index为i1；它的前面(即左面) 每个x上的y值与这个最大点的y值比较；差值 之和 就是 前i1个的水容量。
 *  接着，将i1 看做是 y轴(无限的)，从 i1+1开始还是的 len-1这一段，找最大值，找到后 计算 差值和；依次类推。最终将所有和相加。
 *
 * 用一维数组模拟；只有一个数时，装不了水； 多个数纯倒序时，也装不了水
 *
 * 图示见：石柱注水问题.png
 */
public class al_Water {

    // 若最右边也有一根类似y轴的长柱子
    private static int getSum(int[] ary) {
        int maxIndex = 0;
        for (int i = 0; i < ary.length; i++) {
            if (ary[i] >= ary[maxIndex]) {
                maxIndex = i;
            }
        }
        System.out.println("最大值索引：" + maxIndex);

        int sum = 0;
//        if (maxIndex == 0) {
//            for (int i = 0; i < ary.length; i++) {
//                sum += ary[maxIndex] - ary[i];
//            }
//        } else if (maxIndex == ary.length - 1) {
//            for (int i = maxIndex; i >= 0; i--) {
//                sum += ary[maxIndex] - ary[i];
//            }
//        } else {
            for (int i = 0; i < maxIndex; i++) {
                sum += ary[maxIndex] - ary[i];
            }
            for (int i = maxIndex; i < ary.length; i++) {
                sum += ary[maxIndex] - ary[i];
            }
//        }
        return sum;
    }


    private static int getSum(int[] ary, int l) {
        int maxIndex = l;

        for (int i = l; i < ary.length; i++) {
            if (ary[i] >= ary[maxIndex]) {
                maxIndex = i;
            }
        }
        System.out.println("最大值索引：" + maxIndex);

        int sum = 0;
        for (int i = l; i < maxIndex; i++) {
            sum += ary[maxIndex] - ary[i];
        }
        if (maxIndex + 1 < ary.length)
            sum += getSum(ary, maxIndex + 1);
        return sum;
    }


    public static void main(String[] args) {
//        int[] ary = {2, 1, 2, 4, 1, 2, 3};
//        int[] ary = {2, 1, 2, 4, 1, 2, 3, 3};
//        int[] ary = {2, 1, 2, 4, 1, 2, 3, 3, 1};
//        int[] ary = {2, 1, 2, 4, 1, 2, 3, 3, 1, 2};
        int[] ary = {1,2,4};

        /*
         * 若最右边也有一根类似y轴的长柱子
         */
        System.out.println(getSum(ary));
        System.out.println();

        /*
         * 若最右边没有类似y轴
         */
        System.out.println(getSum(ary, 0));

    }
}
