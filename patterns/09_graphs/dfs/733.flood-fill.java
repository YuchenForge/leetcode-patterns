/*
 * @lc app=leetcode id=733 lang=java
 *
 * [733] Flood Fill
 */

// @lc code=start
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) return image; 

        int originalColor = image[sr][sc];
        dfs(image, sr, sc, originalColor, color);
        return image;
    }

    private void dfs(int[][] image, int row, int col, int originalColor, int color) {
        int m = image.length;
        int n = image[0].length;

        // out of bound
        if (row < 0 || row >= m || col < 0 || col >= n) {
            return;
        }

        // Color doesn't match original or alr visited
        if (image[row][col] != originalColor) {
            return;
        }

        // Change color
        image[row][col] = color;

        // neighbors
        dfs(image, row+1, col, originalColor, color);
        dfs(image, row-1, col, originalColor, color);
        dfs(image, row, col+1, originalColor, color);
        dfs(image, row, col-1, originalColor, color);
    }
}
// @lc code=end

// Time Complexity: O(m × n)
// Space Complexity: O(m × n)