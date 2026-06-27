/*
 * @lc app=leetcode id=94 lang=java
 *
 * [94] Binary Tree Inorder Traversal
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

// Iterative
// Time: O(n)
// Space: O(h)
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            TreeNode node = stack.pop();
            result.add(node.val);
            current = node.right;
        }

        return result;
    }
}

// Recursive
// Time: O(n)
// Space: O(h)
class Solution2 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        inorder(root, result); 
        return result;
    }

    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
}
// @lc code=end

