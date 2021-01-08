package com.stone.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * desc:    https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/  3.无重复字符的最长子串
 * author:  stone
 * email:   aa86799@163.com
 * blog :   https://stone.blog.csdn.net
 * time:    2020/9/6 17:40
 */
public class LongestSubstringWithoutRepeatingCharacters {

    // "abcabcbb" 无重复字符的最长子串 的长度

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0; //快指针，正常step
        int count = 0;
        while (end < s.length()) {
            count = Math.max(count, end - start);

            //abbad
            //用map记录下标，比如字符串abcdecf，到遇到第二个c，即便从bcde任意一个开始，长度都无法超过a，只有从decf开始计算才是新一轮查找
            //当遇到重复值，说明左指针需要跳转，跳转的位置是该重复值的下标+1
            // 比如字符串abcdecf，到遇到第二个c，即便从bcde任意一个开始，长度都无法超过a，只有从decf开始计算才是新一轮查找
            // 值得注意的是，如果碰到了重复值的下标比左指针还小的情况，不应该跳转，因为左指针左边的元素不再窗口内，比如abba
            if (map.containsKey(s.charAt(end)) && map.get(s.charAt(end)) >= start ) {
                start = map.get(s.charAt(end)) + 1;
            }
            map.put(s.charAt(end), end);

            end++;
        }
        count = Math.max(count, end - start);
        return count;
    }
}
