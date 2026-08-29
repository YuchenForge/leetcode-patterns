/*
 * @lc app=leetcode id=452 lang=java
 *
 * [452] Minimum Number of Arrows to Burst Balloons
 */

import java.util.*;

// @lc code=start
class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;

        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int intervals = 0;
        long prevEnd = Long.MIN_VALUE;

        for (int[] point: points) {
            if (point[0] > prevEnd) {
                prevEnd = point[1];
                intervals++;
            } 
        }

        return intervals;
    }
}
// @lc code=end

