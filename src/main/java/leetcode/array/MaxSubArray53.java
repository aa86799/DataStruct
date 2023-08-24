package leetcode.array;

public class MaxSubArray53 {

    public static void main(String[] args) {
//        System.out.println(maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
//        System.out.println(maxSubArray(new int[] {-2, 5, 4, -3}));
        System.out.println(maxSubArray(new int[]{-1, 4, -2, 5, -3, 8, 1}));
    }

    // 动态规划, f(i) = max { f(i-1)+nums[i], nums[i] }
    public static int maxSubArray(int[] nums) {
        if (nums == null) return 0;
        if (nums.length == 1) return nums[0];

        int max = nums[0];
        int pre = 0;
        for (int num : nums) {
            pre = Math.max(pre + num, num); // pre 要么是前 n 个的和+当前值，要么是当前值，两者取最大; 若当前值大，那丢弃了之前的和
            max = Math.max(pre, max); //pre 和历史的 max 比
        }
        return max;
    }
}
