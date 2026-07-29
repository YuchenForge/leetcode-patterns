/*
 * @lc app=leetcode id=210 lang=java
 *
 * [210] Course Schedule II
 */

import java.util.*;

// @lc code=start
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Build graph
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        for (int[] pre: prerequisites) {
            int course = pre[0];
            int prerequisite = pre[1];

            graph.get(prerequisite).add(course);
            indegree[course]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] schedule = new int[numCourses];
        int index = 0;

        while (!queue.isEmpty()) {
            int course = queue.poll();
            schedule[index] = course;
            index++;

            for (int next: graph.get(course)) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // If not all courses were processed, there is a cycle.
        if (index != numCourses) {
            return new int[0];
        }

        return schedule;
    }
}
// @lc code=end

// Time: O(V + E)
// Space: O(V + E)
