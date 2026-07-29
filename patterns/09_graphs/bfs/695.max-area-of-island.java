/*
 * @lc app=leetcode id=695 lang=java
 *
 * [695] Max Area of Island
 */

import java.util.*;

// @lc code=start
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = bfs(grid, i, j);
                    if (area > maxArea) {
                        maxArea = area;
                    }
                }
            }
        }

        return maxArea;
    }

    private int bfs(int[][] grid, int row, int col) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});

        grid[row][col] = 0;
        int area = 0;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            area++;

            for (int[] direction: directions) {
                int newRow = cell[0] + direction[0];
                int newCol = cell[1] + direction[1];

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n
                    && grid[newRow][newCol] == 1) {
                    grid[newRow][newCol] = 0;
                    queue.offer(new int[]{newRow, newCol});
                }  
            }
        }

        return area;
    }
}
// @lc code=end

