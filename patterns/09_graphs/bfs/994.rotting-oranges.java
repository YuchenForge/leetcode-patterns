/*
 * @lc app=leetcode id=994 lang=java
 *
 * [994] Rotting Oranges
 */

import java.util.*;

// @lc code=start
class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Deque<int[]> queue = new ArrayDeque<>();

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int fresh = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    fresh++;
                }
            }
        }


        int minutes = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rottedAny = false;

            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();

                for (int[] neighbor: directions) {
                    int newRow = cell[0] + neighbor[0];
                    int newCol = cell[1] + neighbor[1];

                    if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n 
                    && grid[newRow][newCol] == 1) {
                        rottedAny = true;
                        grid[newRow][newCol] = 2;
                        fresh--;
                        queue.offer(new int[] {newRow, newCol});
                    }
                }
            }

            if (rottedAny) minutes++;
        }

        if (fresh > 0) return -1;

        return minutes;
    }
}
// @lc code=end

// min minute = farthest distance from rotten orange to fresh orange

// Time: O(m * n)
// Space: O(m * n)