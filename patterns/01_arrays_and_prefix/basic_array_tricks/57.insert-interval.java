/*
 * @lc app=leetcode id=57 lang=java
 *
 * [57] Insert Interval
 */

import java.util.*;

// @lc code=start
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new LinkedList<>();
        
        int i = 0;
        int n = intervals.length;

        // 1. No overlap, add everything before new
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // 2. Overlap, merge 
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]); 
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }

        result.add(newInterval);

        // 3. No overlap, add everything after new
        while (i < n) {
            result.add(intervals[i]);
            i++;
        } 
        
        return result.toArray(int[][]::new);

    }
}
// @lc code=end

