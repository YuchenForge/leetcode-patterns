/*
 * @lc app=leetcode id=1851 lang=java
 *
 * [1851] Minimum Interval to Include Each Query
 */

import java.util.*;

// @lc code=start
class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        // Sort intervals by start point
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Store queries with their original indices
        int[][] sortedQueries = new int[queries.length][2];

        for (int i = 0; i < queries.length; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        // Sort queries by value
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[queries.length];

        // Min-heap: [size, end point]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        int i = 0;

        for (int[] query: sortedQueries) {
            int q = query[0];

            // Add all intervals that start before or at q
            while (i < intervals.length && intervals[i][0] <= q) {
                int start = intervals[i][0];
                int end = intervals[i][1];
                int size = end - start + 1;
                minHeap.offer(new int[] {size, end});
                i++;
            }

            // Remove intervals that ended before q
            while (!minHeap.isEmpty() && minHeap.peek()[1] < q) {
                minHeap.poll();
            }

            // Smallest valid interval
            if (!minHeap.isEmpty()) {
                result[query[1]] = minHeap.peek()[0];
            } else {
                result[query[1]] = -1;
            }
        }

        return result;
    }
}
// @lc code=end

