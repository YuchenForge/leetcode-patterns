/*
 * @lc app=leetcode id=630 lang=java
 *
 * [630] Course Schedule III
 */

import java.util.*;

// @lc code=start
class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> (a[1] - b[1]));

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));

        int time = 0;

        for (int[] course: courses) {
            int duration = course[0];
            int last = course[1];

            time += duration;
            maxHeap.offer(duration);

            if (time > last) {
                time -= maxHeap.poll();
            }
        }

        return maxHeap.size();
    }
}
// @lc code=end

// Time:  O(n log n)
// Space: O(n)