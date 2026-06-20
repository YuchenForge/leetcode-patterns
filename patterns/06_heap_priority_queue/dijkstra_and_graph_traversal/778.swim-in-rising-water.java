/*
 * @lc app=leetcode id=778 lang=java
 *
 * [778] Swim in Rising Water
 */

import java.util.*;

// @lc code=start
class Solution {
    public int swimInWater(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] times = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                times[i][j] = Integer.MAX_VALUE;
            }
        }
        times[0][0] = grid[0][0];

        // {time, row, col}
        PriorityQueue<int[]> minHeap = 
            new PriorityQueue<>((a, b) -> a[0] - b[0]);
        minHeap.offer(new int[] {grid[0][0], 0, 0});

        int[][] directions = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        while(!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int time = curr[0];
            int r = curr[1];
            int c = curr[2];

            if (r == rows-1 && c == cols-1) {
                return time;
            }

            if (time > times[r][c]) {
                continue;
            }

            for (int[] dir: directions) {
                int newR = r + dir[0];
                int newC = c + dir[1];

                if (newR >= 0 && newR < rows && newC >= 0 && newC < cols) {
                    int newTime = Math.max(time, grid[newR][newC]);

                    if (newTime < times[newR][newC]) {
                        times[newR][newC] = newTime;
                        minHeap.offer(new int[] {newTime, newR, newC});
                    }
                }
            }
        }

        return times[rows - 1][cols - 1];
    }
}
// @lc code=end

// Time: O(N^2 log N^2)
// Space: O(N^2)
