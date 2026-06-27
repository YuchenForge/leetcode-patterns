/*
 * @lc app=leetcode id=105 lang=java
 *
 * [105] Construct Binary Tree from Preorder and Inorder Traversal
 */

import java.util.*;

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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // value → index
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i); 
        }
        return build(preorder, inorder, 
                    0, preorder.length - 1,
                    0, inorder.length - 1, map);
    }

    private TreeNode build(int[] preorder, int[] inorder,
        int preStart, int preEnd, int inStart, int inEnd,
        Map<Integer, Integer> map) {

        if (preStart > preEnd || inStart > inEnd) return null;
        
        TreeNode root = new TreeNode();
        root.val = preorder[preStart];
        int rootIndex = map.get(root.val);  
        
        int leftSize = rootIndex - inStart;
        root.left = build(preorder, inorder, 
            preStart+1, preStart+leftSize, inStart, rootIndex-1, map);
        root.right = build(preorder, inorder, 
            preStart+leftSize+1, preEnd, rootIndex+1, inEnd, map);
        return root;
    }
}
// @lc code=end

// Time: O(N)
// Space: O(N)
