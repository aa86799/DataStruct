package leetcode.array;

/**
 * desc:   https://leetcode-cn.com/problems/container-with-most-water   no.[11]盛最多水的容器
 * author:  stone
 * email:   aa86799@163.com
 * blog :   https://stone.blog.csdn.net
 * time:    2020/9/5 23:32
 */
public class MostWaters11 {

    public static void main(String[] args) {

    }

    //O(n^2)
    public int maxArea2(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int are = (j-i) * Math.min(height[i], height[j]);
                max = Math.max(are, max);
            }
        }
        return max;
    }

    //O(n)
    public int maxArea(int[] height) {
        int max = 0;
        int i = 0;
        int j = height.length - 1; //最后一个 索引
        while (i < j) { //左索引 < 右索引
            //相距宽度 * 左右中的最小高度 = 面积
            int are = (j-i) * Math.min(height[i], height[j]);
            max = Math.max(are, max);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }

}
