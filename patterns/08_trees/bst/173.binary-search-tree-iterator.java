/*
 * @lc app=leetcode id=173 lang=java
 *
 * [173] Binary Search Tree Iterator
 */

import java.util.*;

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
class BSTIterator {

    private Deque<TreeNode> stack;

    // O(h)
    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        pushLeft(root);
    }
    
    // O(1) time (O(h) worst time complexity) and O(h) space
    public int next() {
        TreeNode next = stack.pop();

        if (next.right != null) {
            TreeNode left = next.right;
            while (left != null) {
                stack.push(left);
                left = left.left;
            }
        }

        return next.val;
    }
    
    // O(1)
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void pushLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
// @lc code=end