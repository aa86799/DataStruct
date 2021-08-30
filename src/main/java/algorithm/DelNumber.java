package algorithm;

import java.util.LinkedList;

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog :   https://stone.blog.csdn.net
 * time:    2020/2/13 01:10
 */
public class DelNumber {
    public static void main(String[] args) {
        /*
         * n 个数字，围成一个圆，从索引0开始，每次间隔 (m-1) 删除第m个数，最后余下 哪个数字？
         */
        int n = 21;//41
        int m = 6; //3
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int i = 0;
        int span = m - 1;//间隔步长
        int vspan = span; //按步长变化后的下一删除索引；相当于初始列表中的索引
        int no = 0; //删除操作的次数
        int alive = n % m; //按步长删除后，留存的个数
        while (list.size() > alive) {// > 1 ，则结果只有一个数； 大于 alive 相当于，在一组m个数中，不重复
            if (i == vspan) {
                System.out.println("i=" + i);
                System.out.println("删除第" + (++no) + "次;" + " 删除的数字是：" + list.remove(i));
                vspan += span;
                if (vspan >= list.size()) {
                    i = vspan = vspan % list.size();
                }
            } else {
                i++;
            }
        }
        System.out.println(list.size());
        System.out.println(list.toString()); //活着的数字索引
        /*
         0 ~ 40, 共41个数时，每隔2个数，左边是删除的索引，右边是余下的索引：
         2 5 8 11 14 17 20 23 26 29 32 35 38
         0 4 9 13 18 22 27 31 36 40         余: 1 3 6 7 10 12 15 16 19 21 24 25 28 30 33 34 37 39
         6 12 19 25 33 39                       1 3 7 10 15 16 21 24 28 30 34 37
         7 16 28 37                             1 3 10 15 21 24 30 34
         10 24                                  1 3 15 21 30 34
         1 21                                   3 15 30 34
         3 34                                   15 30

         */

        del(n, m);
    }

    //适合只余下一个数的操作
    static void del(int n, int m) {
        /*
         * 先是n个数，编号从 1..m..p..n，   m为要删除的位置，p为真实活下的位置；
         * 删除第m个数，余下 n-1个数，重新编号，原m后的数字成为了编号1：
         *          1..p'..n-m,n-m+1,n-m+2,..n-1
         * p 到 p' 的变化：p=(p'+m)%n;
         *
         * 第一次是 n阶环；第二次 n-1阶，... 直到 1 阶
         * 由上分析， n-1阶与n阶 , (p_n-1 + m)%n = p_n
         *
         *
         */
        int p = 0;
        for (int i = 2; i <= n; i++) { //0,1,2   第一次删除操作在i=2时，
            p = (p + m) % i;
        }
        System.out.println(p);

    }
}
