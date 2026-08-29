/*
 * @lc app=leetcode id=435 lang=java
 *
 * [435] Non-overlapping Intervals
 */

import java.util.*;

// @lc code=start
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int removals = 0;
        int prevEnd = Integer.MIN_VALUE;

        for (int[] interval: intervals) {
            if (interval[0] >= prevEnd) {
                prevEnd = interval[1];
            } else {
                removals++;
            }
        }

        return removals;
    }
}
// @lc code=end

// Greedily select the interval with ealiest end time
// Time: O(n log n)
// Space: depends on Arrays.sort() -> O(log n) to O(n)