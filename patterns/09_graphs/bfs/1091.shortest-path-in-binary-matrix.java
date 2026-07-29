/*
 * @lc app=leetcode id=1091 lang=java
 *
 * [1091] Shortest Path in Binary Matrix
 */

import java.util.*;

// @lc code=start
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        if (grid[0][0] == 1) return -1;

        Deque<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[] {0, 0, 1});
        grid[0][0] = 1;

        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1}, {1, 0}, {1, 1}
        };

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            if (cell[0] == n-1 && cell[1] == n-1) {
                return cell[2];
            }

            for (int[] neighbor: directions) {
                int newRow = cell[0] + neighbor[0];
                int newCol = cell[1] + neighbor[1];
                int distance = cell[2];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n 
                    && grid[newRow][newCol] == 0) {
                    grid[newRow][newCol] = 1;
                    queue.offer(new int[] {newRow, newCol, distance + 1});
                }
            }
        }

        return -1;
    }
}
// @lc code=end

// Time: O(n^2)
// Space: O(n^2)