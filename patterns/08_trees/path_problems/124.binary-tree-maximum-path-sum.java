/*
 * @lc app=leetcode id=124 lang=java
 *
 * [124] Binary Tree Maximum Path Sum
 */

// @lc code=start
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
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    // return the best path that includes this node
    private int dfs(TreeNode node) {
        if (node == null) return 0;

        // post order
        int left = dfs(node.left);
        int right = dfs(node.right);

        int sum = node.val + Math.max(0, left) + Math.max(0, right);
        
        maxSum = Math.max(maxSum, sum);

        return Math.max(node.val, 
            Math.max(node.val + left, node.val + right));
    }
}
// @lc code=end

// Time: O(n)
// Space: O(h)