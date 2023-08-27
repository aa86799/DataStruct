package leetcode.dynamicplanning;

/**
 * desc:    整数拆分 https://leetcode-cn.com/problems/integer-break/submissions/
 * author:  stone
 * email:   aa86799@163.com
 * blog :   https://stone.blog.csdn.net
 * time:    2022/2/26 13:43
 */
public class IntegerBreak343 {

    public static void main(String[] args) {
        IntegerBreak343 instance = new IntegerBreak343();
        long time = System.currentTimeMillis();
//        System.out.println(instance.integerBreak1(29));
        System.out.println(instance.integerBreak(29));
        System.out.println(instance.integerBreakDynamic(29));
        System.out.println(System.currentTimeMillis() - time);
    }

    // 动态规划
    int integerBreakDynamic(int n) {
        if (n <= 1) return 1;
        int[] ary = new int[n+1];
        ary[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            // 求 ary[i]，就是求了 最大的乘积
            for (int j = 1; j < i; j++) {
                // i 分成了 j和(i-j)； ary[i-j]，之前求出的
                // j 循环 => ary[i] 不断重新赋值计算
                ary[i] = Math.max(ary[i], Math.max(j*(i-j), j * ary[i-j]));
            }
        }
        return ary[n];
    }

    // 递归+记忆搜索
    public int integerBreak(int n) {
        if (n <= 1) return 1;
        int[] ary = new int[n];
        ary[0] = 1;
        for (int i = 1; i < n; i++) {
            ary[i] = -1;
        }
        return sub(n, ary);
    }

    public int sub(int n, int[] ary) {
        int max = -1;
        if (ary[n-1] != -1) return ary[n-1]; // 记忆搜索
        for (int i = 1; i < n; i++) {
            //  max = Math.max(max, i * (integerBreak(n-i)));
            // i*(n-i) , 只拆成两部分；i * integerBreak(n-i)， 可拆成n部分
            max = Math.max(max, Math.max(i*(n-i), i * sub(n-i, ary)));
        }
        ary[n-1] = max;
        return max;
    }

    // O(n^m)
    public int integerBreak1(int n) {
        if (n <= 1) return 1;
        int max = -1;
        for (int i = 1; i < n; i++) {
            //  max = Math.max(max, i * (integerBreak(n-i)));
            // i*(n-i) , 只拆成两部分；i * integerBreak(n-i)， 可拆成n部分
            max = Math.max(max, Math.max(i*(n-i), i * integerBreak1(n-i)));
        }
        return max;
    }
}
