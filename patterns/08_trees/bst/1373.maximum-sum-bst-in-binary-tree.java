/*
 * @lc app=leetcode id=1373 lang=java
 *
 * [1373] Maximum Sum BST in Binary Tree
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    private int maxSum = 0;

    private static class Info {
        boolean isBST;
        long sum;
        int min;  // Minimum node value in this subtree
        int max;  // Maximum node value in this subtree

        Info(boolean isBST, long sum, int min, int max) {
            this.isBST = isBST;
            this.sum = sum;
            this.min = min;
            this.max = max;
        }
    }

    public int maxSumBST(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private Info dfs(TreeNode node) {
        if (node == null) {
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        Info left = dfs(node.left);
        Info right = dfs(node.right);

        boolean isBST = left.isBST && right.isBST 
            && left.max < node.val && right.min > node.val;

        int minVal = Math.min(node.val, left.min);
        int maxVal = Math.max(node.val, right.max);

        long sum = left.sum + node.val + right.sum;

        if (isBST) {
            maxSum = Math.max(maxSum, (int) sum);
        }

        return new Info(isBST, sum, minVal, maxVal);
    }
}
// @lc code=end

