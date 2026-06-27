/*
 * @lc app=leetcode id=145 lang=java
 *
 * [145] Binary Tree Postorder Traversal
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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;

        TreeNode lastVisited = null;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            TreeNode node = stack.peek();

            if (node.right != null && lastVisited != node.right) {
                current = node.right;
            } else {
                result.add(node.val);
                lastVisited = stack.pop();
            }
        }

        return result;
    }
}

// Recursive
// Time: O(n)
// Space: O(h)
class Solution2 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        postorder(root, result); 
        return result;
    }

    private void postorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        postorder(node.left, list);
        postorder(node.right, list);
        list.add(node.val);
    }
}
// @lc code=end

