/*
 * @lc app=leetcode id=1110 lang=java
 *
 * [1110] Delete Nodes And Return Forest
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
class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        HashSet<Integer> deleteSet = new HashSet<>();

        for (int i = 0; i < to_delete.length; i++) {
            deleteSet.add(to_delete[i]);
        }

        List<TreeNode> forest = new LinkedList();

        TreeNode newRoot = helper(root, deleteSet, forest);

        if (newRoot != null) {
            forest.add(newRoot);
        }

        return forest;
    }

    private TreeNode helper(TreeNode node, Set<Integer> deleteSet, 
        List<TreeNode> forest) {
        
        if (node == null) return null;

        node.left = helper(node.left, deleteSet, forest);
        node.right = helper(node.right, deleteSet, forest);

        boolean deleted = deleteSet.contains(node.val);
        if (deleted) {
            if (node.left != null)
                forest.add(node.left);

            if (node.right != null)
                forest.add(node.right);
            
            return null;
        } else {
            return node;
        }
            
    }
}
// @lc code=end

