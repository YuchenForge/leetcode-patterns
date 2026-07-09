/*
 * @lc app=leetcode id=417 lang=java
 *
 * [417] Pacific Atlantic Water Flow
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // DFS from Pacific edges
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0, Integer.MIN_VALUE);  // left
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, 0, j, Integer.MIN_VALUE);  // top
        }
        
        // DFS from Atlantic edges
        for (int i = 0; i < m; i++) {
            dfs(heights, atlantic, i, n - 1, Integer.MIN_VALUE);  // right
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, atlantic, m - 1, j, Integer.MIN_VALUE);  // bottom
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, boolean[][] visited, int row, int col, int prevHeight) {
        int m = heights.length;
        int n = heights[0].length;

        // Out of bounds
        if (row < 0 || row >= m || col < 0 || col >= n) {
            return;
        }
        
        // Already visited OR can't flow uphill
        if (visited[row][col] || heights[row][col] < prevHeight) {
            return;
        }

        visited[row][col] = true;
        
        dfs(heights, visited, row + 1, col, heights[row][col]);
        dfs(heights, visited, row - 1, col, heights[row][col]);
        dfs(heights, visited, row, col + 1, heights[row][col]);
        dfs(heights, visited, row, col - 1, heights[row][col]);
    }
}
// @lc code=end

// Time: O(m × n)
// Space: O(m × n)