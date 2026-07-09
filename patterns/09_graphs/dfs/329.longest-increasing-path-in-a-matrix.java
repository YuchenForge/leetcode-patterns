/*
 * @lc app=leetcode id=329 lang=java
 *
 * [329] Longest Increasing Path in a Matrix
 */

// @lc code=start
class Solution {
    private int[][] memo;

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        memo = new int[m][n];
        int maxLength = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = dfs(i, j, matrix, Integer.MIN_VALUE);
                if (sum > maxLength) {
                    maxLength = sum;
                }
            }
        }

        return maxLength;
        
    }

    private int dfs(int row, int col, int[][] matrix, int prev) {
        int m = matrix.length;
        int n = matrix[0].length;

        int sum = 1;

        if (row < 0 || row >= m || col < 0 || col >= n) {
            return 0;
        } 

        if (matrix[row][col] <= prev) {
            return 0;
        }

        if (memo[row][col] != 0) {
            return memo[row][col];
        }
        
        sum = Math.max(sum, 1 + dfs(row + 1, col, matrix, matrix[row][col])); 
        sum = Math.max(sum, 1 + dfs(row - 1, col, matrix, matrix[row][col]));
        sum = Math.max(sum, 1 + dfs(row, col + 1, matrix, matrix[row][col]));
        sum = Math.max(sum, 1 + dfs(row, col - 1, matrix, matrix[row][col]));

        memo[row][col] = sum;
        return sum;
    }
}
// @lc code=end

// Time: O(m * n)
// Space: O(m * n)