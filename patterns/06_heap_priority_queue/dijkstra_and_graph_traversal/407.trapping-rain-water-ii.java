/*
 * @lc app=leetcode id=407 lang=java
 *
 * [407] Trapping Rain Water II
 */

import java.util.*;

// @lc code=start
class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;

        // track processed cells
        boolean[][] visited = new boolean[m][n];

        // {height, row, col} sort by smallest height
        PriorityQueue<int[]> minHeap = 
            new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // add boundary cells to heap
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m-1 || j == 0 || j == n-1) {
                    minHeap.offer(new int[] {heightMap[i][j], i, j});
                    visited[i][j] = true;
                }
            }
        }

        int waterTrapped = 0;
        int[][] directions = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int height = curr[0];
            int row = curr[1];
            int col = curr[2];

            for (int[] dir: directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n
                    && visited[newRow][newCol] == false) {
                    
                    if (heightMap[newRow][newCol] < height) {
                        int water = height - heightMap[newRow][newCol];
                        waterTrapped += water;
                    }

                    int newHeight = Math.max(height, heightMap[newRow][newCol]);
                    minHeap.offer(new int[] {newHeight, newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
        }

        return waterTrapped;
    }
}
// @lc code=end

// Time: O(MN log MN)
// Space: O(MN)

