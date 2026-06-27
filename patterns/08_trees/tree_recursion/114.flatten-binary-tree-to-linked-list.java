/*
 * @lc app=leetcode id=114 lang=java
 *
 * [114] Flatten Binary Tree to Linked List
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

// Recursive
class Solution {
    private TreeNode prev = null;
    
    public void flatten(TreeNode root) {
        if (root == null) return;

        if (prev != null) {
            prev.right = root;
        }
        prev = root;

        TreeNode left = root.left;
        TreeNode right = root.right;
        
        root.left = null;  
        
        flatten(left);   
        flatten(right);
    }
}

// Iterative
class Solution2 {
    public void flatten(TreeNode root) {
        TreeNode node = root;
        
        while (node != null) {
            if (node.left != null) {
                // Find rightmost node in left subtree
                TreeNode rightmost = node.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }
                
                // Connect rightmost to node's right subtree
                rightmost.right = node.right;
                
                // Move left subtree to right
                node.right = node.left;
                node.left = null;
            }
            
            node = node.right;
        }
    }
}
// @lc code=end

// previous.right = current node

// Time: O(n)
// Space: O(h) -> O(1) if using iterative

