/*
 * @lc app=leetcode id=1631 lang=java
 *
 * [1631] Path With Minimum Effort
 */

import java.util.*;

// @lc code=start
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        int[][] efforts = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                efforts[i][j] = Integer.MAX_VALUE;
            }
        }
        efforts[0][0] = 0;

        // {effort, row, col}
        PriorityQueue<int[]> minHeap = 
            new PriorityQueue<>((a, b) -> a[0] - b[0]);
        minHeap.offer(new int[] {0, 0, 0});

        int[][] directions = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        while(!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int effort = curr[0];
            int r = curr[1];
            int c = curr[2];

            if (r == rows-1 && c == cols-1) {
                return effort;
            }

            if (effort > efforts[r][c]) {
                continue;
            }

            for (int[] dir: directions) {
                int newR = r + dir[0];
                int newC = c + dir[1];

                if (newR >= 0 && newR < rows && newC >= 0 && newC < cols) {
                    int diff = Math.abs(heights[r][c] - heights[newR][newC]);
                    int newEffort = Math.max(effort, diff);

                    if (newEffort < efforts[newR][newC]) {
                        efforts[newR][newC] = newEffort;
                        minHeap.offer(new int[] {newEffort, newR, newC});
                    }
                }
            }
        }

        return efforts[rows - 1][cols - 1];
    }
}

// Time: O(mn log(mn))
// Space: O(mn)
// @lc code=end

