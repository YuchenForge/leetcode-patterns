/*
 * @lc app=leetcode id=56 lang=java
 *
 * [56] Merge Intervals
 */

import java.util.*;

// @lc code=start
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();

        for (int[] interval: intervals) {
            if (!merged.isEmpty() && interval[0] <= merged.get(merged.size()-1)[1]) {
                merged.get(merged.size()-1)[1] = 
                    Math.max(merged.get(merged.size()-1)[1], interval[1]);
            } else {
                merged.add(interval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
// @lc code=end

// Time: O(n log n) (sorting dominates)
// Space: O(n)
