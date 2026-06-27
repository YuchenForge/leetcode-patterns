/*
 * @lc app=leetcode id=113 lang=java
 *
 * [113] Path Sum II
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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        dfs(root, targetSum, path, result);

        return result;
    }

    private void dfs(TreeNode node, int remaining,
        List<Integer> path, List<List<Integer>> result) {

        if (node == null) return;

        path.add(node.val);
        remaining -= node.val;

        if (node.left == null && node.right == null) {
            if (remaining == 0) {
                result.add(new ArrayList<>(path));
            }
        } else {
            dfs(node.left, remaining, path, result);
            dfs(node.right, remaining, path, result);
        }

        // backtrack
        path.remove(path.size() - 1);
    }
}
// @lc code=end

// Time: O(N + H)
// Space: O(H)