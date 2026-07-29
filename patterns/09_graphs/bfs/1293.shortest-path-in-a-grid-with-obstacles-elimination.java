/*
 * @lc app=leetcode id=1293 lang=java
 *
 * [1293] Shortest Path in a Grid with Obstacles Elimination
 */

import java.util.*;

// @lc code=start
class Solution {
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        if (m == 1 && n == 1) {
            return 0;
        }

        // visited[r][c][number of obstacles removed]
        boolean[][][] visited = new boolean[m][n][k + 1];

        Queue<int[]> queue = new LinkedList<>();

        // {row, col, removed, steps}
        queue.offer(new int[]{0, 0, 0, 0});
        visited[0][0][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();

            int row = cell[0];
            int col = cell[1];
            int removed = cell[2];
            int steps = cell[3];

            for (int i = 0; i < 4; i++) {
                int newRow = row + dr[i];
                int newCol = col + dc[i];

                // Outside grid
                if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n) {
                    continue;
                }

                int newRemoved = removed;

                // obstacle
                if (grid[newRow][newCol] == 1) {
                    newRemoved++;
                }

                if (newRemoved > k) {
                    continue;
                }

                // Destination
                if (newRow == m - 1 && newCol == n - 1) {
                    return steps + 1;
                }

                // not visited with this elimination count
                if (!visited[newRow][newCol][newRemoved]) {
                    visited[newRow][newCol][newRemoved] = true;
                    queue.offer(new int[]{newRow, newCol,
                        newRemoved, steps + 1});
                }
            }
        }

        return -1;
    }
}
// @lc code=end

// Time: O(m * n * k)
// Space: O(m * n * k)