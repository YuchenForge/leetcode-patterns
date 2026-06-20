/*
 * @lc app=leetcode id=378 lang=java
 *
 * [378] Kth Smallest Element in a Sorted Matrix
 */

import java.util.*;

// @lc code=start
// Priority Queue Approach
// Time: O(K log N)
// Space: O(K)
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        // [value, row, col]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );

        // fill with first col
        for (int row = 0; row < n; row++) {
            minHeap.offer(new int[] {matrix[row][0], row, 0});
        }

        int res = -1;
        for (int i = 0; i < k; i++) {
            int[] curr = minHeap.poll();
            res = curr[0];
            int row = curr[1];
            int col = curr[2];
            
            if (col + 1 < n) {
                minHeap.offer(new int[] {matrix[row][col+1], row, col+1});
            }
        }
        
        return res;
    }
}
// @lc code=end

// Binary Search Approach
// Time: O(N log D) where D is hi - lo
// Space: O(1)
class Solution2 {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0];
        int hi = matrix[n - 1][n - 1] ;
        
        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (countLessOrEqual(mid, matrix, k)) {
                ans = mid;
                hi = mid - 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    // staircase traversal
    private boolean countLessOrEqual(int mid, int[][] matrix, int k) {
        int count = 0;
        int n = matrix.length;

        // start from top right
        int col = n - 1; 
        for (int row = 0; row < n; row++) {
            while (col >= 0 && matrix[row][col] > mid) {
                col--;  
            }
            count += (col + 1);
        }
        return count >= k;
    }
}