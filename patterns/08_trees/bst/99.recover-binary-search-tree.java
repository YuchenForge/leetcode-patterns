/*
 * @lc app=leetcode id=99 lang=java
 *
 * [99] Recover Binary Search Tree
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
    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode prev = null;

    public void recoverTree(TreeNode root) {
        inorder(root);

        int temp = first.val;
        first.val = second.val;
        second.val = temp;

    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);

        if (prev != null && prev.val > node.val) {
            if (first == null) {
                first = prev;
                second = node;
            } else {
                second = node;
            }
        }

        prev = node;
        inorder(node.right);
    }
}
// @lc code=end

// Adjacent: [1, 3, 2, 4]
// Non-adjacent: [3, 2, 1, 4]

// Time: O(n)
// Space: O(h)