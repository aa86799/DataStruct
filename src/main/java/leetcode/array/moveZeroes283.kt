package leetcode.array

//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 示例:
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0]
//
// 说明:
//
//
// 必须在原数组上操作，不能拷贝额外的数组。
// 尽量减少操作次数。
//
// Related Topics 数组 双指针 👍 1201 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun moveZeroes(nums: IntArray): Unit {

    }

    //时间复杂度：O(n)
    private fun solutionA(nums: IntArray) {
        var j = 0 // 指向0数可能在的位置
//        for (i in 0 until nums.size) {
        for (i in nums.indices) {
            if (nums[i] != 0) {
                swap(nums, i, j)
                j++
            }
        }
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i] + nums[j]
        nums[i] = temp - nums[i]
        nums[j] = temp - nums[j]
    }

    // [1,0,0,2]
    /*
     *  1 0 0 2     j = 1
     *  1 0 0 2
     *  1 0 0 2     i=2, j= 1
     *  1 2 0 2     i=3,j=2
     */
    private fun solutionB(nums: IntArray) {
        var j = 0 // 指向0数可能在的位置，首位置可能非0
        for (i in 0 until nums.size) {
            if (nums[i] != 0) {
                nums[j++] = nums[i] // 非0数，赋给可能为0的位置
            }
        }
        while (j < nums.size) { // j之前可能为0的位置已被赋值为非0数，所以其与之后要置0
            nums[j++] = 0
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
