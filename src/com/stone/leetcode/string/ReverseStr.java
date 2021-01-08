package com.stone.leetcode.string;

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog :   https://stone.blog.csdn.net
 * time:    2021/1/8 11:52
 */
public class ReverseStr {

    private static String reverse(String src) {
        if (src == null || src.length() <= 1) return src;
        int start = 0;
        int end = src.length() - 1;
        char temp;
        char[] ary = src.toCharArray();
        while (start < end) {
            temp = ary[start];
            ary[start++] = ary[end];
            ary[end--] = temp;
        }
        return String.valueOf(ary);
    }

    public static void main(String[] args) {
        String reverse = reverse("stone");
        System.out.println(reverse);
    }
}
