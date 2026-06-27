/*
 * @lc app=leetcode id=103 lang=java
 *
 * [103] Binary Tree Zigzag Level Order Traversal
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();

        if (root == null) return result;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int dir = 1;

        while (!queue.isEmpty()) {
            int level = queue.size();
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < level; i++) {
                TreeNode node = queue.peek();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                list.add(queue.poll().val);
            }
            if (dir == 1) {
                result.add(list);
            } else {
                result.add(list.reversed());
            }

            dir *= -1;
        }

        return result;
    }
}
// @lc code=end

// Time: O(n)
// Space: O(w)
