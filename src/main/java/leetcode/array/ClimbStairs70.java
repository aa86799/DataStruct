package leetcode.array;

/**
 * desc:    https://leetcode-cn.com/problems/climbing-stairs/  no. [70]爬楼梯
 * author:  stone
 * email:   aa86799@163.com
 * blog :   https://stone.blog.csdn.net
 * time:    2020/9/6 00:29
 */
public class ClimbStairs70 {

    public int climbStairs(int n) {
        //1. 1
        //2. 1+1, 2,  2
        //3. 111, 12, 21,   3
        //4. 1111,121, 211, 112, 22 5
        //有 fibonacci 数列规律
        int p = 0, q = 0;
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                p = q = i;
            } else if (i == 2) {
                q = i;
            } else {
                int temp = p + q;
                p = q;
                q = temp;
            }
        }
        return q;
    }
}
