/*
 * @lc app=leetcode id=437 lang=java
 *
 * [437] Path Sum III
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
    public int pathSum(TreeNode root, int targetSum) {
        HashMap<Long, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0L, 1);

        return dfs(root, 0L, targetSum, prefixMap);
    }

    private int dfs(TreeNode node, long currentSum, int targetSum, 
        HashMap<Long, Integer> prefixMap) {
        
        if (node == null) return 0;
        currentSum += node.val;

        // count = # of paths end at this node and sum to targetSum
        int count = prefixMap.getOrDefault(currentSum - targetSum, 0);

        prefixMap.put(currentSum, prefixMap.getOrDefault(currentSum, 0) + 1);

        count += dfs(node.left, currentSum, targetSum, prefixMap);
        count += dfs(node.right, currentSum, targetSum, prefixMap);

        // backtrack
        prefixMap.put(currentSum, prefixMap.getOrDefault(currentSum, 0) - 1);

        return count;
    }
}
// @lc code=end


// Time: O(n)
// Space: O(h)
