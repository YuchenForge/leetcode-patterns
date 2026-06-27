/*
 * @lc app=leetcode id=297 lang=java
 *
 * [297] Serialize and Deserialize Binary Tree
 */

import java.util.*;

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Codec {
    // Time: O(n)
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    private void preorder(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#");
            sb.append(",");
            return;
        } else {
            sb.append(node.val);
            sb.append(",");
        }

        preorder(node.left, sb);
        preorder(node.right, sb);
    }

    // Time: O(n)
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] values = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(values));
        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
        String val = queue.poll(); 
        if (val.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = buildTree(queue);
        root.right = buildTree(queue);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
// @lc code=end

