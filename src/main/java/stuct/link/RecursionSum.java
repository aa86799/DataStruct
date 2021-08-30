package stuct.link;

/**
 * desc  : 运用递归 求和  n1+...nn   或  nn+...n1
 * author: stone
 * email : aa86799@163.com
 * time  : 2018/12/26 09 32
 */
public class RecursionSum {

    public static void main(String[] args) {
        int[] ary = new int[100];
        for (int i = 0, len = ary.length; i < len; i++) {
            ary[i] = i + 1;
        }

        System.out.println(sum(ary, 0));
        System.out.println(sum2(ary, ary.length - 1));
    }

    private static int sum(int[] ary, int index) {
        if (index >= ary.length || index < 0) {//递归中 最基本问题
            return 0;
        }
        return ary[index] + sum(ary, ++index); //把原问题( n1+n2 ) 转化成更小的问题 ，采用递归式
    }

    private static int sum2(int[] ary, int index) {
        if (index >= ary.length || index < 0) {
            return 0;
        }
        return ary[index] + sum2(ary, --index);
    }
}
