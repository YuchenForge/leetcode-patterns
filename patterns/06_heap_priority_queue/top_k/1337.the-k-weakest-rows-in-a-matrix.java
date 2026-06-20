/*
 * @lc app=leetcode id=1337 lang=java
 *
 * [1337] The K Weakest Rows in a Matrix
 */

import java.util.*;

// @lc code=start
class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;

        // order by (count, index)
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0];  
            }
            return b[1] - a[1];     
        });

        for (int i = 0; i < m; i++) {
            int count = countSoldiers(mat[i]);
            maxHeap.offer(new int[]{count, i});

            // evict the strongest
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = maxHeap.poll()[1];
        }
        return result;   
    }

    private int countSoldiers(int[] row) {
        int count = 0;
        int n = row.length;
        for (int i = 0; i < n; i++) {
            if (row[i] == 1) {
                count++;
            }
        }

        return count;
    }
}

// Time: O(mn + m log k)
// Space: O(k)
// @lc code=end

