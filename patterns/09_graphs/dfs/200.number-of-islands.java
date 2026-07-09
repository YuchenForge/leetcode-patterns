/*
 * @lc app=leetcode id=200 lang=java
 *
 * [200] Number of Islands
 */

// @lc code=start
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int row, int col) {
        int m = grid.length;
        int n = grid[0].length;

        if (row < 0 || row >= m || col < 0 || col >= n) {
            return;
        } 

        if (grid[row][col] == '0') {
            return;
        }

        grid[row][col] = '0';
        
        dfs(grid, row + 1, col); 
        dfs(grid, row - 1, col);
        dfs(grid, row, col + 1);
        dfs(grid, row, col - 1);
    }
}
// @lc code=end

// Time: O(m × n)
// Space: O(m × n)