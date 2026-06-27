/*
 * @lc app=leetcode id=144 lang=java
 *
 * [144] Binary Tree Preorder Traversal
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
// Space O(h)
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) return result;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return result;
    
    }
}

// Recursive
// Time: O(n)
// Space O(h)
class Solution2 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorderList = new LinkedList<>();
        preorder(root, preorderList);
        return preorderList;
    }
    
    private void preorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);   
        preorder(node.left, list);
        preorder(node.right, list); 
    }
}
// @lc code=end

