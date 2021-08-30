package leetcode.tree;

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog :   https://stone.blog.csdn.net
 * time:    2020/9/29 12:07
 */
public class BinaryTreeMaxPathSum124 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxPathSum(TreeNode root) {
//        return Math.max(sideMax(root), max);
        sideMax(root);
        return max;
    }

    int max = Integer.MIN_VALUE;
    public int sideMax(TreeNode root) {
        //不一定经过根节点；要包含 max(左,右) -> 它们的父节点这样的路径。
        if (root == null) return 0;
        int left = Math.max(0, sideMax(root.left));
        /*
         * 与0相比，好处是，若子路径为负数，max() => 0
         * 当前节点是负 或 非负
         * 后续 left(0) + root.val ， =>  root.val
         * 不论当前节点是负或非负，这时 当前节点就是最大值，
         *      因题目说不一定要经过子节点；
         *      若当前为负，那两个负相加，一定更小了，用 max(0, cur) 没问题；
         *      若当前为正，那当前一定是比(当前+left)大的， 用max(0,cur)没问题；
         */
        int right = Math.max(0, sideMax(root.right));
        max = Math.max(max, left + right + root.val);
        return Math.max(left, right) + root.val;
    }


}
