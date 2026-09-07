/*
 * @lc app=leetcode id=1024 lang=java
 *
 * [1024] Video Stitching
 */

import java.util.*;

// @lc code=start
class Solution {
    public int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, (a, b) -> Integer.compare(a[0], b[0]));

        int covered = 0;
        int farthest = 0;

        int count = 0;
        int i = 0;

        while (covered < time) {
            while (i < clips.length && clips[i][0] <= covered) {
                farthest = Math.max(farthest, clips[i][1]);
                i++;
            }

            if (farthest <= covered) {
                return -1;
            }

            count++;
            covered = farthest;
        }

        return count;
    }
}
// @lc code=end

// Time: O(n log n)