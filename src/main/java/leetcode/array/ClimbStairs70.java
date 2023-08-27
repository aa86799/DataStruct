package leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * desc:    https://leetcode-cn.com/problems/climbing-stairs/  no. [70]爬楼梯
 * author:  stone
 * email:   aa86799@163.com
 * blog :   https://stone.blog.csdn.net
 * time:    2020/9/6 00:29
 */
public class ClimbStairs70 {

    static int count = 0;
    static int setCount = 0;
    static long[] nums;
    static HashMap<Integer, Long> hashMap;
    public static void main(String[] args) {
        int n = 30;
        nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = -1;
        }

        hashMap = new HashMap<>(n+10);

        long startTime = System.currentTimeMillis();
//        System.out.println(climbStairs(n) + ", count=" + count);
//        System.out.println(climbStairs2(n) + ", count=" + count + ", setCount=" + setCount);
//        System.out.println(climbStairs3(n) + ", count=" + count + ", setCount=" + setCount);
        System.out.println(climbStairs4(n) + ", count=" + count + ", setCount=" + setCount);
        System.out.println(System.currentTimeMillis() - startTime);
    }

    // 递归 Fibonacci  最差解
    public static long climbStairs2(int n) {
        count++;
        if (n <= 0) return 0;
        if (n <= 2) return n;
        setCount++;
        return climbStairs2(n - 1) + climbStairs2(n - 2);
    }

    // 递归 + 记忆搜索(数组记忆), 去除重复计算
    public static long climbStairs3(int n) {
        count++;
        if (n <= 0) return 0;
        if (n <= 2) return n;
        if (nums[n - 1] == -1) {
            setCount++;
            nums[n - 1] = climbStairs3(n - 1) + climbStairs3(n - 2);
        }
        return nums[n-1];
    }

    // 递归 + 记忆搜索(map记忆)
    public static long climbStairs4(int n) {
        count++;
        if (n <= 0) return 0;
        if (n <= 2) return n;
        if (!hashMap.containsKey(n)) {
            setCount++;
            hashMap.put(n, climbStairs4(n - 1) + climbStairs4(n - 2));
        }
        return hashMap.get(n);
    }

    // 循环实现
    public static long climbStairs(int n) {
        //1. 1
        //2. 1+1, 2,  2
        //3. 111, 12, 21,   3
        //4. 1111,121, 211, 112, 22 5
        //有 fibonacci 数列规律
        if (n <= 0) return 0;
        long a = 0, b = 0;
        for (int i = 1; i <= n; i++) {
            count++;
            if (i == 1) {
                a = b = i;
            } else if (i == 2) {
                b = i;
            } else {
                b = a + b;
                a = b - a;
            }
        }
        return b;
    }

    public int climbStairs2(int n) {
        if (n <= 0) return 0;
        if (n <= 2) return n;
        int a = 1; int b = 2;
        int i = 2;
        while (i < n) {
            b = a + b;
            a = b - a;
            i++;
        }
        return b;
    }

    // 递归
    public int climbStairs3(int n) {
         if (n <= 0) return 0;
         if (n <= 2) return n;
         return climbStairs3(n-1) + climbStairs3(n-2);
    }

}
